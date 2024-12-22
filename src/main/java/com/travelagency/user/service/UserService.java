package com.travelagency.user.service;

import com.travelagency.user.domain.User;
import com.travelagency.user.dto.LoginRequest;
import com.travelagency.user.dto.RegisterRequest;
import com.travelagency.user.storage.UserStorage;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserStorage userStorage;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserStorage userStorage, PasswordEncoder passwordEncoder) {
        this.userStorage = userStorage;
        this.passwordEncoder = passwordEncoder;
    }
    
    public void registerUser(RegisterRequest request) {
        if (userStorage.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (userStorage.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        
        userStorage.save(user);
    }

    public void login(LoginRequest request) {
        User user = userStorage.findByUsername(request.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
    }
}