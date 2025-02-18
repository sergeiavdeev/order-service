package ru.avdeev.order_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.ConditionDto;
import ru.avdeev.order_service.dto.OrderDto;
import ru.avdeev.order_service.dto.OrderProductDto;
import ru.avdeev.order_service.mapper.OrderMapper;
import ru.avdeev.order_service.repository.OrderRepository;
import ru.avdeev.order_service.service.ConditionService;
import ru.avdeev.order_service.service.OrderProductService;
import ru.avdeev.order_service.service.OrderService;
import ru.avdeev.order_service.service.TariffService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderProductService orderProductService;
    private final TariffService tariffService;
    private final ConditionService conditionService;
    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public Mono<OrderDto> saveOrder(OrderDto order) {
        return orderRepository.save(orderMapper.toEntity(order))
                .map(savedOrder -> {
                    for (OrderProductDto product : order.getProducts()) {
                        product.setOrderId(savedOrder.id());
                    }
                    return order.getProducts();
                })
                .flatMapMany(Flux::fromIterable)
                .flatMap(product -> setProductSum(product, order.getUserId()))
                .flatMap(orderProductService::save)
                .collectList()
                .map(pList -> {
                    order.setProducts(pList);
                    return order;
                });
    }

    @Override
    public Mono<OrderDto> cancelOrder(UUID orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::toDto)
                .map(order -> {
                    order.setIsCancel(true);
                    return orderMapper.toEntity(order);
                })
                .flatMap(orderRepository::save)
                .map(orderMapper::toDto);
    }

    @Override
    public Flux<OrderDto> getAll(UUID userId) {
        return orderRepository.findOrderByUserId(userId)
                .map(orderMapper::toDto)
                .flatMap(order -> orderProductService.getByOrderId(order.getId())
                        .map(productList -> {
                            order.setProducts(productList);
                            return order;
                        })
                );
    }

    private Mono<OrderProductDto> setProductSum(OrderProductDto product, UUID userId) {
        return conditionService.getCondition(product.getProductId(), userId, product.getCount())
                .map(ConditionDto::getSum)
                .switchIfEmpty(
                        tariffService.getTariff(product.getProductId(), product.getCount())
                                .map(tariff -> tariff.getPrice() * product.getCount())
                )
                .switchIfEmpty(Mono.just(0d))
                .map(sum -> {
                    log.info("Set product sum: {}", sum);
                    product.setSum(sum);
                    return product;
                });
    }
}
