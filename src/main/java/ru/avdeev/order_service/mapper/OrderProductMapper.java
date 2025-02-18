package ru.avdeev.order_service.mapper;

import org.mapstruct.Mapper;
import ru.avdeev.order_service.dto.OrderProductDto;
import ru.avdeev.order_service.entity.OrderProduct;

@Mapper(componentModel = "spring")
public interface OrderProductMapper {

    OrderProduct toEntity(OrderProductDto dto);
    OrderProductDto toDto(OrderProduct entity);
}
