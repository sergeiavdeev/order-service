package ru.avdeev.order_service.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.OrderProductDto;
import ru.avdeev.order_service.mapper.OrderProductMapper;
import ru.avdeev.order_service.repository.OrderProductRepository;
import ru.avdeev.order_service.service.OrderProductService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProductServiceImpl implements OrderProductService {

    private final OrderProductRepository orderProductRepository;
    private final OrderProductMapper orderProductMapper;

    @Override
    public Mono<OrderProductDto> save(OrderProductDto orderProductDto) {
        return orderProductRepository.add(orderProductMapper.toEntity(orderProductDto))
                .map(orderProductMapper::toDto);
    }

    @Override
    public Mono<List<OrderProductDto>> getByOrderId(UUID orderId) {
        return orderProductRepository.findByOrderId(orderId)
                .map(orderProductMapper::toDto)
                .collectList();
    }
}
