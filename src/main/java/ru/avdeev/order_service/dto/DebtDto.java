package ru.avdeev.order_service.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class DebtDto {

    private UUID id;
    private LocalDateTime createdAt;
    private UUID orderId;
    private Double dt;
    private Double kt;
}
