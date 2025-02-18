package ru.avdeev.order_service.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("order_product")
public record OrderProduct(
        @Id
        UUID id,
        @Column("order_id")
        UUID orderId,
        @Column("product_id")
        UUID productId,
        @Column("count")
        Double count,
        @Column("sum")
        Double sum
) {
}
