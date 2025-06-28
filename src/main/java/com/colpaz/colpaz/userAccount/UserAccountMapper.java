package com.colpaz.colpaz.userAccount;

import com.colpaz.colpaz.role.Role;
import com.colpaz.colpaz.role.RoleResponse;
import org.springframework.stereotype.Component;

@Component
public class UserAccountMapper {

    public UserAccountResponse toResponse(UserAccount user) {
        RoleResponse roleDTO = new RoleResponse();
        roleDTO.setId(user.getRole().getId());
        roleDTO.setName(user.getRole().getName());
        roleDTO.setCreatedBy(user.getRole().getCreatedBy());

        UserAccountResponse dto = new UserAccountResponse();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setActive(user.isActive());
        dto.setCreatedBy(user.getCreatedBy());
        dto.setRole(roleDTO);
        return dto;
    }

    public UserAccount toEntity(UserAccountRequest request, Role role) {
        UserAccount user = new UserAccount();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setActive(request.isActive());
        user.setCreatedBy(request.getCreatedBy());
        user.setRole(role);
        return user;
    }
}