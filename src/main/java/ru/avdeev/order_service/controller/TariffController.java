package ru.avdeev.order_service.controller;

import jakarta.ws.rs.QueryParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.dto.ConditionDto;
import ru.avdeev.order_service.dto.TariffDto;
import ru.avdeev.order_service.mapper.ConditionMapper;
import ru.avdeev.order_service.mapper.TariffMapper;
import ru.avdeev.order_service.service.ConditionService;
import ru.avdeev.order_service.service.TariffService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("v1/tariff")
public class TariffController {

    private final TariffService tariffService;
    private final ConditionService conditionService;

    @GetMapping("")
    public Mono<TariffDto> getTariff(@RequestParam UUID productId, @RequestParam Double count) {

        return conditionService.getCondition(productId, null, count)
                .switchIfEmpty(Mono.just(new ConditionDto()))
                .zipWith(tariffService.getTariff(productId, count)
                        .switchIfEmpty(Mono.just(new TariffDto()))
                )
                .map(t -> {
                   t.getT2().setCondition(t.getT1());
                   t.getT2().setSum(t.getT2().getPrice() * count);
                   return t.getT2();
                });
    }
}
