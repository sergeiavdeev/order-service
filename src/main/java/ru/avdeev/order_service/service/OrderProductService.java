package ru.avdeev.order_service.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.OrderProductDto;
import ru.avdeev.order_service.repository.OrderProductRepository;

import java.util.List;
import java.util.UUID;


public interface OrderProductService {

    Mono<OrderProductDto> save(OrderProductDto orderProductDto);
    Mono<List<OrderProductDto>> getByOrderId(UUID orderId);
}
