package ru.avdeev.order_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.ConditionDto;
import ru.avdeev.order_service.mapper.ConditionMapper;
import ru.avdeev.order_service.repository.ConditionRepository;
import ru.avdeev.order_service.service.ConditionService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConditionServiceImpl implements ConditionService {

    private final ConditionRepository conditionRepository;
    private final ConditionMapper conditionMapper;

    @Override
    public Mono<ConditionDto> getCondition(UUID productId, UUID userId, Double count) {
        return conditionRepository.getCondition(productId, userId, count)
                .map(conditionMapper::toDto);
    }
}
