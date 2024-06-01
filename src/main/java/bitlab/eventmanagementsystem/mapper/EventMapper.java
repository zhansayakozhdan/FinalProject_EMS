package bitlab.eventmanagementsystem.mapper;

import bitlab.eventmanagementsystem.dto.EventDto;
import bitlab.eventmanagementsystem.entity.Event;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EventMapper {
    EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);

    EventDto toDto(Event event);
}
