package com.colpaz.colpaz.role;

import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleResponse toResponse(Role role) {
        RoleResponse dto = new RoleResponse();
        dto.setId(role.getId());
        dto.setName(role.getName());
        dto.setCreatedBy(role.getCreatedBy());
        return dto;
    }

    public Role toEntity(RoleRequest dto) {
        Role role = new Role();
        role.setName(dto.getName());
        role.setCreatedBy(dto.getCreatedBy());
        return role;
    }
}