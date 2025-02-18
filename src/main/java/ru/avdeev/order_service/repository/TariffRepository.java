package ru.avdeev.order_service.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.entity.Tariff;

import java.util.UUID;

@Repository
public interface TariffRepository extends ReactiveCrudRepository<Tariff, UUID> {

    @Query(tariffQuery)
    Mono<Tariff> getTariff(UUID productId, Double count);

    String tariffQuery = """
            select *
            from tariff
            where product_id = :productId
              and rate = (select max(rate)
                          from tariff
                          where product_id = :productId
                            and rate <= :count)
            limit 1
            ;
            """;
}
