package ru.avdeev.order_service.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.avdeev.order_service.entity.Condition;

import java.util.UUID;

public interface ConditionRepository extends ReactiveCrudRepository<Condition, UUID> {

    @Query(conditionQuery)
    Mono<Condition> getCondition(UUID productId, UUID userId, Double count);

    String conditionQuery = """
            select t.*
            from (select *, 1 as exact
                  from condition
                  where product_id = :productId
                    and user_id = :userId
                    and count = :count
                  union all
                  select *, 0
                  from condition
                  where product_id = :productId
                    and user_id = :userId
                    and count is null
                  union all
                  select *, 0
                  from condition
                  where product_id = :productId
                    and count = :count
                    and condition.user_id is null) t
            order by t.exact desc
            limit 1
            ;
            """;
}
