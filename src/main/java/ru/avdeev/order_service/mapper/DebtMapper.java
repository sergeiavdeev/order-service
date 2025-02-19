package ru.avdeev.order_service.mapper;

import org.mapstruct.Mapper;
import ru.avdeev.order_service.dto.DebtDto;
import ru.avdeev.order_service.entity.Debt;

@Mapper(componentModel = "spring")
public interface DebtMapper {
    Debt toEntity(DebtDto debtDto);
    DebtDto toDto(Debt debt);
}
