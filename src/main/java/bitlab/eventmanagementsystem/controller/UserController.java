package bitlab.eventmanagementsystem.controller;

import bitlab.eventmanagementsystem.dto.UserCreate;
import bitlab.eventmanagementsystem.restController.UserRestController;
import bitlab.eventmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/create")
    public String createUser(UserCreate userCreate){
        String statusMessage = userService.createUser(userCreate);
        return "redirect:/login?statusMessage="+statusMessage;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public String profilePage(){
        return "profile";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/admin/panel")
    public String adminPage(){
        return "adminPanel";
    }

    //Only for Admin
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/details/{id}")
    public String userDetailsPage(@PathVariable Long id){
        return "userDetails";
    }
}
