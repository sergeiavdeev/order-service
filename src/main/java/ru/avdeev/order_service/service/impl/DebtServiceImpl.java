package ru.avdeev.order_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.DebtDto;
import ru.avdeev.order_service.mapper.DebtMapper;
import ru.avdeev.order_service.repository.DebtRepository;
import ru.avdeev.order_service.service.DebtService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DebtServiceImpl implements DebtService {

    private final DebtRepository debtRepository;
    private final DebtMapper debtMapper;

    @Override
    public Mono<DebtDto> addKt(UUID orderId, Double amount) {
        var debt = new DebtDto();
        debt.setOrderId(orderId);
        debt.setDt(0d);
        debt.setKt(amount);
        return debtRepository.save(debtMapper.toEntity(debt))
                .map(debtMapper::toDto);
    }

    @Override
    public Mono<DebtDto> addDt(UUID orderId, Double amount) {
        var debt = new DebtDto();
        debt.setOrderId(orderId);
        debt.setDt(amount);
        debt.setKt(0d);
        return debtRepository.save(debtMapper.toEntity(debt))
                .map(debtMapper::toDto);
    }

    @Override
    public Mono<UUID> delete(UUID orderId) {
        return debtRepository.deleteByOrderId(orderId)
                .map(id -> orderId);
    }

    @Override
    public Flux<DebtDto> getDebts(UUID orderId) {
        return debtRepository.findByOrderId(orderId)
                .map(debtMapper::toDto);
    }
}
