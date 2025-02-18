package ru.avdeev.order_service.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class BookingDto {
    private UUID id;
    private UUID resourceId;
    private UUID userId;
    private Double count;
}
