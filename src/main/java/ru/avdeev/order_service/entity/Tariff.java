package ru.avdeev.order_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

public record Tariff(
        @Id
        UUID id,
        @Column("product_id")
        UUID productId,
        @Column("rate")
        Double rate,
        @Column("price")
        Double price
) {
}
