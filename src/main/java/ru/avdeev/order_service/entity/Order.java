package ru.avdeev.order_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Table("\"order\"")
public record Order(

        @Id
        UUID id,
        @Column("user_id")
        UUID userId,
        @Column("created_at")
        LocalDateTime createdAt,
        @Column("updated_at")
        LocalDateTime updatedAt,
        @Column("is_cancel")
        Boolean isCancel,
        @Column("order_date")
        LocalDate orderDate,
        @Column("number")
        String number
) {
}
