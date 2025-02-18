package ru.avdeev.order_service.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
public class ConditionDto {

    private UUID id;
    private UUID productId;
    private Double count;
    private UUID userId;
    private Double sum;
}
