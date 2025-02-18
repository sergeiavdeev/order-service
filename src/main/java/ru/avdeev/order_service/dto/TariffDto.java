package ru.avdeev.order_service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import java.util.UUID;

@Data
public class TariffDto {
    private UUID id;
    private UUID productId;
    private Double rate;
    private Double price;
    private ConditionDto condition;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Double sum;
}
