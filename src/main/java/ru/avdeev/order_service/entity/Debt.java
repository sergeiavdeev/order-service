package ru.avdeev.order_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.UUID;

public record Debt(
        @Id
        UUID id,
        @Column("created_at")
        LocalDateTime createdAt,
        @Column("order_id")
        UUID orderId,
        @Column("dt")
        Double dt,
        @Column("kt")
        Double kt
) {
}
