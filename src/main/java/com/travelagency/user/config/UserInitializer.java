package com.travelagency.user.config;

import com.travelagency.user.domain.User;
import com.travelagency.user.storage.UserStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserInitializer implements CommandLineRunner {

    private final UserStorage userStorage;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInitializer(UserStorage userStorage, PasswordEncoder passwordEncoder) {
        this.userStorage = userStorage;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        // Only add test users if no users exist
        if (!userStorage.existsByUsername("john.doe")) {
            // Create test users
            createUser(
                "john.doe",
                "john@example.com",
                "password123",
                "1234567890"
            );

            createUser(
                "jane.smith",
                "jane@example.com",
                "password456",
                "9876543210"
            );

            createUser(
                "bob.wilson",
                "bob@example.com",
                "password789",
                "5555555555"
            );
        }
    }

    private void createUser(String username, String email, String password, String phone) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setPhone(phone);
        userStorage.save(user);
    }
} 