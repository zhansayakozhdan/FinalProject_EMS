package bitlab.eventmanagementsystem.restController;

import bitlab.eventmanagementsystem.dto.EventDto;
import bitlab.eventmanagementsystem.entity.Event;
import bitlab.eventmanagementsystem.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventRestController {
    private final EventService eventService;

    @GetMapping
    public List<EventDto> getAllEvents(){
        return eventService.getAllEvents();
    }

    @GetMapping("{id}")
    public EventDto getEventById(@PathVariable Long id){
        return eventService.getEventByIdForView(id);
    }

    @PostMapping
    public EventDto createEvent(@RequestBody Event event){
        return eventService.createEvent(event);
    }

    @PutMapping
    public EventDto editEvent(@RequestBody Event event){
        return eventService.editEvent(event);
    }

    @DeleteMapping("{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEventById(id);
    }

    @GetMapping("/search")
    public List<EventDto> searchEvents(@RequestParam("query") String query) {
        return eventService.searchEvents(query);
    }
}
