package com.accordiq.config;

import com.accordiq.role.entity.Role;
import com.accordiq.role.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) {

        createRoleIfNotExists(
                "ROLE_USER",
                "Default role for all registered users"
        );

        createRoleIfNotExists(
                "ROLE_ADMIN",
                "Administrator role"
        );
    }

    private void createRoleIfNotExists(String name, String description) {

        if (roleRepository.findByName(name).isEmpty()) {

            Role role = Role.builder()
                    .name(name)
                    .description(description)
                    .build();

            roleRepository.save(role);

            System.out.println("Created role: " + name);
        }
    }
}