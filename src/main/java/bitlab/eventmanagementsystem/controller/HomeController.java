package bitlab.eventmanagementsystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

//    @PreAuthorize("isAuthenticated()")
    @GetMapping("/")
    public String homePage() {
        return "home";
    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER', 'ROLE_ORGANIZER')")
    @GetMapping("/event/view/{id}")
    public String eventDetailsForView(@PathVariable Long id){
        return "eventView";
    }


}
