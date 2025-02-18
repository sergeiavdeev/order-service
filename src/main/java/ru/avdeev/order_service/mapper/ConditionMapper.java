package ru.avdeev.order_service.mapper;

import org.mapstruct.Mapper;
import ru.avdeev.order_service.dto.ConditionDto;
import ru.avdeev.order_service.entity.Condition;

@Mapper(componentModel = "spring")
public interface ConditionMapper {
    ConditionDto toDto(Condition condition);
    Condition toEntity(ConditionDto conditionDto);
}
