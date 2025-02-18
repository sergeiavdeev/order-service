package ru.avdeev.order_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

public record Condition(
        @Id
        UUID id,
        @Column("product_id")
        UUID productId,
        @Column("count")
        Double count,
        @Column("user_id")
        UUID userId,
        @Column("sum")
        Double sum
) {
}
