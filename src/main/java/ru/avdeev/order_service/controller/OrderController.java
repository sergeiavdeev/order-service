package ru.avdeev.order_service.controller;

import io.netty.handler.codec.http.HttpResponseStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.OrderDto;
import ru.avdeev.order_service.exception.ApiException;
import ru.avdeev.order_service.service.OrderService;

import java.util.UUID;

@RestController
@RequestMapping("v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("")
    public Flux<OrderDto> getOrders(@AuthenticationPrincipal Jwt jwt) {
        if (jwt == null) {
            throw new ApiException(HttpResponseStatus.UNAUTHORIZED, "Unauthorized");
        }
        UUID userId = UUID.fromString(jwt.getClaim("sub").toString());

        return orderService.getAll(userId);
    }

    @GetMapping("/{id}")
    public Mono<OrderDto> getByOrderProductId(@PathVariable("id") UUID orderProductId) {
        return orderService.getOrderByOrderProductId(orderProductId);
    }
}
