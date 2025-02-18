package ru.avdeev.order_service.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.TariffDto;
import ru.avdeev.order_service.mapper.TariffMapper;
import ru.avdeev.order_service.repository.TariffRepository;
import ru.avdeev.order_service.service.TariffService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TariffServiceImpl implements TariffService {

    private final TariffRepository tariffRepository;
    private final TariffMapper tariffMapper;

    @Override
    public Mono<TariffDto> getTariff(UUID productId, Double count) {
        return tariffRepository.getTariff(productId, count)
                .map(tariffMapper::toDto);
    }
}
