package bitlab.eventmanagementsystem.service;

import bitlab.eventmanagementsystem.dto.*;
import bitlab.eventmanagementsystem.entity.Role;
import bitlab.eventmanagementsystem.entity.User;
import bitlab.eventmanagementsystem.exception.SourceNotFoundException;
import bitlab.eventmanagementsystem.mapper.RoleMapper;
import bitlab.eventmanagementsystem.mapper.UserMapper;
import bitlab.eventmanagementsystem.repository.RoleRepository;
import bitlab.eventmanagementsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final RoleService roleService;

    public String createUser(UserCreate userCreate) {
        log.info("Creating user with EMAIL: {}", userCreate.getEmail());
        Optional<User> optionalUser = userRepository.findByEmail(userCreate.getEmail());
        if (optionalUser.isPresent()) {
            return "EMAIL IS BUSY!";
        }

        if (!userCreate.getPassword().equals(userCreate.getRePassword())) {
            return "PASSWORDS ARE NOT SAME!";
        }

        final String encodedPassword = passwordEncoder.encode(userCreate.getPassword());
        userCreate.setPassword(encodedPassword);

        User user = UserMapper.INSTANCE.toEntity(userCreate);
        Role roleUser = roleRepository.findRoleUser();
        user.setRoles(Collections.singletonList(roleUser));
        //List.of(role1, role2, role3)
        //Collections.singletonList(userRole) если мы знаем что по дефолту толька одна роль будет задана

        userRepository.save(user);
        return "ACCOUNT CREATED SUCCESSFULLY!";
    }

    public User getCurrentUser() {
        log.info("Current user: {}", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof AnonymousAuthenticationToken) {
            return null;
        } else {
            return (User) authentication.getPrincipal();
        }
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    public UserDto getUserDto() {
        User user = getUserById(getCurrentUser().getId());
        return UserMapper.INSTANCE.toDto(user);
    }

    public UserViewDto getUserView(Long id) {
        User user = getUserById(id);
        return UserMapper.INSTANCE.toViewDto(user);
    }

    public UserViewDto editUserByAdmin(User user) {
        log.info("Editing user by admin: User EMAIL: {}", user.getEmail());
        user.setPassword(getUserById(user.getId()).getPassword());
        User saved = userRepository.save(user);
        return UserMapper.INSTANCE.toViewDto(saved);
    }



    public UserDto changeUserPassword(UserUpdate userUpdate) {
        log.info("Changing User Password UserId: {}", userUpdate.getId());
        User user = getUserById(userUpdate.getId());

        if (passwordEncoder.matches(userUpdate.getCurrentPassword(), user.getPassword())) {
            if (userUpdate.getNewPassword().equals(userUpdate.getReNewPassword())) {
                // Установка нового пароля
                final String encodedPassword = passwordEncoder.encode(userUpdate.getNewPassword());
                user.setPassword(encodedPassword);

                User saved = this.userRepository.save(user);
                return UserMapper.INSTANCE.toDto(saved);
            } else {
                throw new RuntimeException("NEW PASSWORDS ARE NOT SAME!");
            }
        } else {
            throw new RuntimeException("OLD PASSWORDS ARE NOT SAME!");
        }
    }

    public void deleteUserById(Long id) {
        log.info("Deleting user by id: {}", id);
        userRepository.deleteById(id);
    }

    public List<UserViewDto> getAllUsers() {
        log.info("Getting all users for ADMIN view");
        List<User> users = userRepository.findAll();
        List<UserViewDto> userDtoList = UserMapper.INSTANCE.toDtoList(users);
        return userDtoList;
    }

    public UserViewDto addRoleForUser(Long userId, Long roleId) throws SourceNotFoundException {
        log.info("Adding role for user, userID: {}", userId);
        User user = getUserById(userId);
        Role role = roleService.getRoleById(roleId);
        if (user == null || role == null) {
            return null;
        }
        List<Role> roles = user.getRoles();
        roles.add(role);

        User saved = userRepository.save(user);
        return UserMapper.INSTANCE.toViewDto(saved);
    }

    public UserViewDto deleteRoleFromUser(Long userId, Long roleId) throws SourceNotFoundException {
        log.info("Deleting role from user, userID: {}", userId);
        User user = getUserById(userId);
        Role role = roleService.getRoleById(roleId);
        if (user == null || role == null) {
            return null;
        }
        List<Role> roles = user.getRoles();
        roles.remove(role);
        User saved = userRepository.save(user);
        return UserMapper.INSTANCE.toViewDto(saved);
    }

    public List<RoleDto> getRoleListForAdding(Long userId) {
        log.info("Getting roles for user, userID: {}", userId);
        User user = getUserById(userId);
        List<Role> roles = roleService.getAllRoles();
        roles.removeAll(user.getRoles());
        List<RoleDto> roleDtoList = new ArrayList<>();
        for (Role role : roles) {
            RoleDto roleDto = RoleMapper.INSTANCE.toDto(role);
            roleDtoList.add(roleDto);
        }
        return roleDtoList;
    }

}
