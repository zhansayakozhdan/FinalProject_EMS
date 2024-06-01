package bitlab.eventmanagementsystem.service;

import bitlab.eventmanagementsystem.dto.EventDto;
import bitlab.eventmanagementsystem.entity.Event;
import bitlab.eventmanagementsystem.mapper.EventMapper;
import bitlab.eventmanagementsystem.repository.EventRepository;
import bitlab.eventmanagementsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class EventService {
    private final EventRepository eventRepository;
    private final EventTypeService eventTypeService;
    private final UserRepository userRepository;

    public EventDto createEvent(Event event) {
        log.info("Creating event  NAME: {}", event.getName());
        Event saved = eventRepository.save(event);
        return EventMapper.INSTANCE.toDto(saved);
    }

    public EventDto editEvent(Event event) {
        log.info("Updating event  NAME: {}", event.getName());
        Event saved = eventRepository.save(event);
        return EventMapper.INSTANCE.toDto(saved);
    }

    public List<EventDto> getAllEvents() {
        log.info("Getting all events");
        List<Event> events = eventRepository.findAll();
        List<EventDto> eventDtos = new ArrayList<>();
        for (Event event : events) {
            EventDto eventDto = EventMapper.INSTANCE.toDto(event);
            eventDtos.add(eventDto);
        }
        return eventDtos;
    }

    public EventDto getEventByIdForView(Long id) {
        log.info("Getting eventDto by id {}", id);
        Event event = eventRepository.findById(id).orElseThrow();
        return EventMapper.INSTANCE.toDto(event);
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id).orElseThrow();
    }

    public void deleteEventById(Long id) {
        log.info("Deleting event by id {}", id);
        eventRepository.deleteById(id);
    }

    public List<EventDto> searchEvents(String query) {
        log.info("Searching for events with query {}", query);
        List<Event> events = eventRepository.search(query);
        List<EventDto> eventDtoList = new ArrayList<>();
        for (Event event : events) {
            EventDto eventDto = EventMapper.INSTANCE.toDto(event);
            eventDtoList.add(eventDto);
        }
        return eventDtoList;
    }
}
