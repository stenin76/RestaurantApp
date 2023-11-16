package com.softuni.Restaurant.init;

import com.softuni.Restaurant.model.Role;
import com.softuni.Restaurant.model.enums.UserRoles;
import com.softuni.Restaurant.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class RoleInit implements CommandLineRunner {

    private RoleRepository roleRepository;

    @Autowired
    public RoleInit(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.roleRepository.count() == 0) {

            List<UserRoles> rolesList = Arrays.stream(UserRoles.values()).toList();
            for (UserRoles ur : rolesList) {
                Role role = new Role();
                role.setUserRoles(ur);
                this.roleRepository.save(role);
            }
        }

    }
}
