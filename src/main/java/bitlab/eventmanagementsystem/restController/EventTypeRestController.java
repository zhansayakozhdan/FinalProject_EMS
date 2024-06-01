package bitlab.eventmanagementsystem.restController;

import bitlab.eventmanagementsystem.dto.EventTypeDto;
import bitlab.eventmanagementsystem.service.EventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/eventType")
@RequiredArgsConstructor
public class EventTypeRestController {
    private final EventTypeService eventTypeService;

    @GetMapping
    public List<EventTypeDto> getEventTypeList(){
        return eventTypeService.getEventTypeList();
    }
}
