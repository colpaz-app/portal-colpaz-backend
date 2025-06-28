package com.colpaz.colpaz;

import com.colpaz.colpaz.role.Role;
import com.colpaz.colpaz.role.RoleRepository;
import com.colpaz.colpaz.userAccount.UserAccount;
import com.colpaz.colpaz.userAccount.UserAccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepository roleRepository;
    private final UserAccountRepository userAccountRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner init() {
        return args -> {
            // Crear roles si no existen
            Role adminRole = createRoleIfNotExists("ADMINISTRADOR");
            Role contentRole = createRoleIfNotExists("ADMINISTRADOR_CONTENIDO");

            // Crear usuario administrador si no existe
            Optional<UserAccount> existingAdmin = userAccountRepository.findByUsername("admin");
            if (existingAdmin.isEmpty()) {
                UserAccount adminUser = new UserAccount();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("admin123"));
                adminUser.setActive(true);
                adminUser.setCreatedBy("sistema");
                adminUser.setRole(adminRole);
                userAccountRepository.save(adminUser);
                System.out.println("🟢 Usuario admin creado.");
            } else {
                System.out.println("ℹ️ Usuario admin ya existe.");
            }
        };
    }

    private Role createRoleIfNotExists(String name) {
        return roleRepository.findByName(name).orElseGet(() -> {
            Role role = new Role();
            role.setName(name);
            role.setCreatedBy("sistema");
            return roleRepository.save(role);
        });
    }
}