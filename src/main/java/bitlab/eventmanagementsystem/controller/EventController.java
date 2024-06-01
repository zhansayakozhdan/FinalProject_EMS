package bitlab.eventmanagementsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class EventController {
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ORGANIZER')")
    @GetMapping("/organizer/panel")
    public String allEventsPage(){
        return "organizerPanel";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ORGANIZER')")
    @GetMapping("/event/details/{id}")
    public String eventDetailsPage(@PathVariable Long id){
        return "eventDetails";
    }


}
