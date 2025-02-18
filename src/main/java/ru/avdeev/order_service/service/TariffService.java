package ru.avdeev.order_service.service;

import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.TariffDto;

import java.util.UUID;

public interface TariffService {

    Mono<TariffDto> getTariff(UUID productId, Double count);
}
