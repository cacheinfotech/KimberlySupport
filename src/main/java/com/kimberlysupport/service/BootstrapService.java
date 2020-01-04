package com.kimberlysupport.service;


import com.kimberlysupport.model.User;
import com.kimberlysupport.repository.UserRepository;
import com.kimberlysupport.util.enums.Gender;
import com.kimberlysupport.util.enums.Role;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

@Log
@Service
public class BootstrapService {

    @Autowired
    BCryptPasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;
    public void execute() {
        createAdmin();
        createUser();
    }

    private void createAdmin() {
        log.info("Creating admin..");
        if (userRepository.countAllByRole(Role.ADMIN) == 0) {
            User user = new User();
            user.setRole(Role.ADMIN);
            user.setGender(Gender.Male);
            user.setName("Admin");
            user.setEmail("admin@demo.com");
            user.setPassword(encoder.encode("abcd1234"));
            userRepository.save(user);
            log.info("Completed creating admin");
        } else {
            log.info("Admin already exists skipping....");
        }

    }

    private void createUser() {
        log.info("Creating random users..");
        if (userRepository.countAllByRole(Role.USER)==0) {
            List<String> names = Arrays.asList("Demo");
            IntStream.range(0, names.size()).forEach(idx -> {

                User user = new User();
                user.setName(names.get(idx));
                user.setRole(Role.USER);
                user.setGender(Gender.Male);
                user.setEmail(names.get(idx).toLowerCase()+ "@demo.com");
                user.setPassword(encoder.encode("abcd1234"));
                userRepository.save(user);
            });

            log.info("Completed creating users");
        } else {
            log.info("Admin already exists skipping....");
        }
    }

}
