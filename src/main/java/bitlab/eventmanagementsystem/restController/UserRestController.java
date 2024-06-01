package bitlab.eventmanagementsystem.restController;

import bitlab.eventmanagementsystem.dto.*;
import bitlab.eventmanagementsystem.entity.Role;
import bitlab.eventmanagementsystem.entity.User;
import bitlab.eventmanagementsystem.exception.SourceNotFoundException;
import bitlab.eventmanagementsystem.repository.RoleRepository;
import bitlab.eventmanagementsystem.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;
    private final RoleRepository roleRepository;

    @GetMapping
    public List<UserViewDto> getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("{id}")
    public UserViewDto getUserView(@PathVariable Long id){
        return userService.getUserView(id);
    }

    @PostMapping
    public String createUser(@RequestBody UserCreate userCreate){
        return userService.createUser(userCreate);
    }

    @PutMapping
    public UserViewDto editUser(@RequestBody User userEdit){
        return userService.editUserByAdmin(userEdit);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUserById(id);
    }

    @PatchMapping("{id}")
    public UserViewDto addRoleForUser(@PathVariable Long id, @RequestBody Role role) throws SourceNotFoundException {
        return userService.addRoleForUser(id, role.getId());
    }

    @PatchMapping(value = "{id}", params = "roleId")
    public UserViewDto deleteRoleFromUser(@PathVariable Long id, @RequestParam Long roleId) throws SourceNotFoundException {
        return userService.deleteRoleFromUser(id, roleId);
    }

    @GetMapping("/profile/")
    public UserDto getUserDto(){
        return userService.getUserDto();
    }

    @PutMapping("/changePassword")
    public UserDto changePassword(@RequestBody UserUpdate userUpdate){
        return userService.changeUserPassword(userUpdate);
    }

}
