package com.colpaz.colpaz.userAccount.services;

import com.colpaz.colpaz.role.Role;
import com.colpaz.colpaz.role.services.RoleService;
import com.colpaz.colpaz.userAccount.*;
import com.colpaz.colpaz.userAccount.exceptions.UsernameAlreadyExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccountServiceImpl implements UserAccountService {

    private final UserAccountRepository userRepository;
    private final RoleService roleService;
    private final UserAccountMapper userMapper;

    @Override
    public UserAccountResponse createUser(UserAccountRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("El usuario con username '" + request.getUsername() + "' ya existe.");
        }

        Role role = roleService.findById(request.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Rol no encontrado con ID: " + request.getRoleId()));

        UserAccount user = userMapper.toEntity(request, role);
        return userMapper.toResponse(userRepository.save(user));
    }
}