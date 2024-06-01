package bitlab.eventmanagementsystem.mapper;

import bitlab.eventmanagementsystem.dto.EventTypeDto;
import bitlab.eventmanagementsystem.entity.EventType;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventTypeMapper {
    bitlab.eventmanagementsystem.mapper.EventTypeMapper INSTANCE = Mappers.getMapper(bitlab.eventmanagementsystem.mapper.EventTypeMapper.class);

    EventTypeDto toDto(EventType eventType);
}
