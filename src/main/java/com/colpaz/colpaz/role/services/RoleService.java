package com.colpaz.colpaz.role.services;

import com.colpaz.colpaz.role.Role;
import com.colpaz.colpaz.role.RoleRequest;
import com.colpaz.colpaz.role.RoleResponse;

import java.util.Optional;

public interface RoleService {
    RoleResponse createRole(RoleRequest request);
    Optional<Role> findById(Long id);
}
