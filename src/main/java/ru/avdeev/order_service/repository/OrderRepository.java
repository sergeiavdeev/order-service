package ru.avdeev.order_service.repository;

import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import ru.avdeev.order_service.entity.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, UUID> {

    Flux<Order> findOrderByUserId(UUID userId);
}
