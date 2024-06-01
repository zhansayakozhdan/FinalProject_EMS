package bitlab.eventmanagementsystem.mapper;

import bitlab.eventmanagementsystem.dto.*;
import bitlab.eventmanagementsystem.entity.Role;
import bitlab.eventmanagementsystem.entity.User;
import bitlab.eventmanagementsystem.repository.RoleRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    //так как здесь у нас методы не статик мы не можем ее просто вызвать в другом классе
    // поэтому и пишем данную строку чтобы мы могли вызвать все методы в другом классе

    User toEntity(UserCreate userCreate);

    UserDto toDto(User user);
    UserViewDto toViewDto(User user);
    List<UserViewDto> toDtoList(List<User> users);
    User toEditedUser(UserDto userDto);


//    @Mapping(target = "roles", expression = "java(mapToRole(dto, roleRepository))")
//    User toEntity(UserEditDto dto, RoleRepository roleRepository);

//    default List<Role> mapToRole(UserEditDto dto, RoleRepository roleRepository) {
//        List<Role> roles = new ArrayList<>();
//        for (String roleName : dto.getRoles()) {
//            Role role = roleRepository.findByName(roleName);
//            roles.add(role);
//        }
//        return roles;
//    }

}
