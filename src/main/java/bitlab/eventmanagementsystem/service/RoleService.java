package bitlab.eventmanagementsystem.service;

import bitlab.eventmanagementsystem.dto.RoleDto;
import bitlab.eventmanagementsystem.entity.Role;
import bitlab.eventmanagementsystem.entity.User;
import bitlab.eventmanagementsystem.exception.SourceNotFoundException;
import bitlab.eventmanagementsystem.mapper.RoleMapper;
import bitlab.eventmanagementsystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService {
    public final RoleRepository roleRepository;

    public Role getRoleById(Long id) throws SourceNotFoundException {
        String message = String.format("Role with ID: %s NOT FOUND", id);
        return roleRepository.findById(id)
                .orElseThrow(() -> new SourceNotFoundException(message));
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

}
