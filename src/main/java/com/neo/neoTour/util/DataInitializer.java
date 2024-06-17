
package com.neo.neoTour.util;

import com.neo.neoTour.entity.Role;
import com.neo.neoTour.entity.User;
import com.neo.neoTour.repository.RoleRepository;
import com.neo.neoTour.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    /*
    @Autowired
    private PasswordEncoder passwordEncoder;

     */

    @Override
    public void run(String... args) throws Exception {
        Role userRole = roleRepository.findByName("ROLE_USER").orElseGet(() -> roleRepository.save(new Role("ROLE_USER")));
        Role adminRole = roleRepository.findByName("ROLE_ADMIN").orElseGet(() -> roleRepository.save(new Role("ROLE_ADMIN")));

        /*
        // Create an admin user
        if (!userRepository.existsByPhoneNumber("admin-phone-number")) {
            User admin = new User();
            admin.setUsername("Admin");
            admin.setPhoneNumber("admin-phone-number");
            admin.setPassword(passwordEncoder.encode("adminpassword"));
            admin.addRole(adminRole);
            userRepository.save(admin);
        }

         */
    }



}


