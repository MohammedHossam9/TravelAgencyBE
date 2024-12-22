package com.travelagency.user.controller;

import com.travelagency.user.dto.Response;
import com.travelagency.user.dto.LoginRequest;
import com.travelagency.user.dto.RegisterRequest;
import com.travelagency.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<Response> registerUser(@RequestBody RegisterRequest request) {
        try {
            userService.registerUser(request);
            return ResponseEntity.ok(new Response(true, "User registered successfully"));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(new Response(false, exception.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody LoginRequest request) {
        try {
            userService.login(request);
            return ResponseEntity.ok(new Response(true, "Login successful"));
        } catch (RuntimeException exception) {
            return ResponseEntity.badRequest().body(new Response(false, exception.getMessage()));
        }
    }
} 