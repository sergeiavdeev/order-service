package ru.avdeev.order_service.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.entity.OrderProduct;

import java.util.UUID;

@Repository
public interface OrderProductRepository extends ReactiveCrudRepository<OrderProduct, UUID> {

    @Query(ADD_QUERY)
    Mono<OrderProduct> add(@Param("product") OrderProduct product);
    Flux<OrderProduct> findByOrderId(UUID orderId);

    String ADD_QUERY = """
        insert into order_product
        values (
            :#{#product.id()},
            :#{#product.orderId()},
            :#{#product.productId()},
            :#{#product.count()},
            :#{#product.sum()})
        """;
}
