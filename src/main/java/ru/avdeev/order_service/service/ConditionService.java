package ru.avdeev.order_service.service;

import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.ConditionDto;

import java.util.UUID;

public interface ConditionService {

    Mono<ConditionDto> getCondition(UUID productId, UUID userId, Double count);
}
