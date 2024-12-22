package com.travelagency.user.dto;

import lombok.Data;         //creates getters setters and default constructors automatically

@Data
public class LoginRequest {
    private String username;
    private String password;
} 