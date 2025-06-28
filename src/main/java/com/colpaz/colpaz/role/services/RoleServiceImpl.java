package com.colpaz.colpaz.role.services;

import com.colpaz.colpaz.role.*;
import com.colpaz.colpaz.role.exceptions.RoleAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    @Override
    public RoleResponse createRole(RoleRequest request) {
        if (roleRepository.findByName(request.getName()).isPresent()) {
            throw new RoleAlreadyExistsException("El rol con nombre '" + request.getName() + "' ya existe.");
        }

        Role role = new Role();
        role.setName(request.getName());
        role.setCreatedBy(request.getCreatedBy());
        return roleMapper.toResponse(roleRepository.save(role));
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepository.findById(id);
    }
}