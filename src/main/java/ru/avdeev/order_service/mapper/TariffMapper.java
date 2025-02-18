package ru.avdeev.order_service.mapper;

import org.mapstruct.Mapper;
import ru.avdeev.order_service.dto.TariffDto;
import ru.avdeev.order_service.entity.Tariff;

@Mapper(componentModel = "spring")
public interface TariffMapper {
    TariffDto toDto(Tariff tariff);
    Tariff toEntity(TariffDto tariffDto);
}
