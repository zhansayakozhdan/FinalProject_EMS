package bitlab.eventmanagementsystem.restController;

import bitlab.eventmanagementsystem.dto.RoleDto;
import bitlab.eventmanagementsystem.entity.Role;
import bitlab.eventmanagementsystem.entity.User;
import bitlab.eventmanagementsystem.service.RoleService;
import bitlab.eventmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleRestController {
    private final UserService userService;

    @GetMapping("{id}")
    public List<RoleDto> getRoleList(@PathVariable Long id){
        return userService.getRoleListForAdding(id);
    }
}
