package ru.avdeev.order_service.mapper;

import org.mapstruct.Mapper;
import ru.avdeev.order_service.dto.OrderDto;
import ru.avdeev.order_service.entity.Order;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderDto toDto(Order order);
    Order toEntity(OrderDto orderDto);
}
