package bitlab.eventmanagementsystem.mapper;

import bitlab.eventmanagementsystem.dto.RoleDto;
import bitlab.eventmanagementsystem.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RoleMapper {
    RoleMapper INSTANCE = Mappers.getMapper(RoleMapper.class);

    RoleDto toDto(Role role);
}
