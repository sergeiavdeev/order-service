package ru.avdeev.order_service.repository;

import com.netflix.appinfo.ApplicationInfoManager;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.entity.Order;

import java.util.UUID;

@Repository
public interface OrderRepository extends ReactiveCrudRepository<Order, UUID> {

    Flux<Order> findOrderByUserId(UUID userId);

    @Query(ORDER_BY_ORDER_PRODUCT_ID_QUERY)
    Mono<Order> findByOrderProductId(UUID id);

    String ORDER_BY_ORDER_PRODUCT_ID_QUERY = """
            select *
            from "order"
            where id = (
                select order_id from order_product
                where id = :id
                )
            ;
            """;
}
