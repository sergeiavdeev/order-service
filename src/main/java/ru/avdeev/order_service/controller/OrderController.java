package ru.avdeev.order_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import ru.avdeev.order_service.dto.OrderDto;
import ru.avdeev.order_service.service.OrderService;

@RestController
@RequestMapping("v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public Flux<OrderDto> getOrders() {
        return orderService.getAll(null);
    }
}
