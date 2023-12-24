package com.mymahallah.mm_user.config;

import com.mymahallah.mm_user.entities.ERole;
import com.mymahallah.mm_user.entities.Role;
import com.mymahallah.mm_user.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class RoleInitializer implements CommandLineRunner {
    private final RoleRepository roleRepository;

    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Check if roles exist, if not, initialize them
        if (roleRepository.count() == 0) {
            Role roleAdmin = new Role(1, ERole.ROLE_ADMIN);
            Role roleUser = new Role(2, ERole.ROLE_USER);
            Role roleStaff = new Role(3, ERole.ROLE_STAFF);
            Role roleStudent = new Role(4, ERole.ROLE_STUDENT);
            // ... create other roles as needed

            log.info("Preloading role database....");

            roleRepository.saveAll(List.of(roleStudent, roleStaff, roleAdmin, roleUser));
        }
    }
}
