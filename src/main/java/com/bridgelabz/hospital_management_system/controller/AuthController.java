package com.bridgelabz.hospital_management_system.controller;

import com.bridgelabz.hospital_management_system.dto.AuthRequest;
import com.bridgelabz.hospital_management_system.dto.AuthResponse;
import com.bridgelabz.hospital_management_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(
            @RequestBody AuthRequest request) {

        String message =
                authService.registerUser(request);

        return AuthResponse.builder()
                .message(message)
                .build();
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest request) {

        String token = authService.loginUser(request);

        return AuthResponse.builder()
                .token(token)
                .message("Login Successful")
                .build();
    }
}