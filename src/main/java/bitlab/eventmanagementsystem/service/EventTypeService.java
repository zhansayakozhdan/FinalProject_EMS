package bitlab.eventmanagementsystem.service;

import bitlab.eventmanagementsystem.dto.EventTypeDto;
import bitlab.eventmanagementsystem.entity.EventType;
import bitlab.eventmanagementsystem.mapper.EventTypeMapper;
import bitlab.eventmanagementsystem.repository.EventTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventTypeService {
    private final EventTypeRepository eventTypeRepository;
    public List<EventTypeDto> getEventTypeList() {
        log.info("Get all EventType list");
        List<EventType> eventTypes = eventTypeRepository.findAll();
        List<EventTypeDto> eventTypeDtos = new ArrayList<>();
        for (EventType eventType : eventTypes) {
            eventTypeDtos.add(EventTypeMapper.INSTANCE.toDto(eventType));
        }
        return eventTypeDtos;
    }
}
