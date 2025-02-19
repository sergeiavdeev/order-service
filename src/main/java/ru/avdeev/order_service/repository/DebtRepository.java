package ru.avdeev.order_service.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.entity.Debt;

import java.util.UUID;

@Repository
public interface DebtRepository extends ReactiveCrudRepository<Debt, UUID> {
   Mono<UUID> deleteByOrderId(UUID orderId);
   Flux<Debt> findByOrderId(UUID orderId);
}
