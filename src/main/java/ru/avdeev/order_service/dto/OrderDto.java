package ru.avdeev.order_service.dto;

import lombok.Data;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
public class OrderDto {
    private UUID id;
    private UUID userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Boolean isCancel;
    private LocalDate orderDate;
    private String number;
    List<OrderProductDto> products;
}
