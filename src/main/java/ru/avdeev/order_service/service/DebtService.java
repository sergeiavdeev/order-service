package ru.avdeev.order_service.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.DebtDto;
import ru.avdeev.order_service.entity.Debt;

import java.util.UUID;

public interface DebtService {

    Mono<DebtDto> addKt(UUID orderId, Double amount);
    Mono<DebtDto> addDt(UUID orderId, Double amount);
    Mono<UUID> delete(UUID orderId);
    Flux<DebtDto> getDebts(UUID orderId);
}
