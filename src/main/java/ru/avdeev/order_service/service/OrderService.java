package ru.avdeev.order_service.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.OrderDto;
import ru.avdeev.order_service.entity.Order;

import java.util.UUID;

public interface OrderService {

    Mono<OrderDto> saveOrder(OrderDto order);
    Mono<OrderDto> cancelOrder(UUID orderId);
    Flux<OrderDto> getAll(UUID userId);
}
