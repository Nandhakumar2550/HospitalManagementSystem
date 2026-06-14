package com.bridgelabz.hospital_management_system.service;

import com.bridgelabz.hospital_management_system.dto.AuthRequest;
import com.bridgelabz.hospital_management_system.entity.Role;
import com.bridgelabz.hospital_management_system.entity.User;
import com.bridgelabz.hospital_management_system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.bridgelabz.hospital_management_system.config.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public String registerUser(AuthRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "User already exists";
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ADMIN)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public String loginUser(AuthRequest request) {

        User user = userRepository
                .findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return null;
        }

        boolean validPassword =
                passwordEncoder.matches(
                        request.getPassword(),
                        user.getPassword());

        if (!validPassword) {
            return null;
        }

        return jwtService.generateToken(user.getEmail());
    }

    public boolean authenticate(String email, String password) {

        return userRepository.findByEmail(email)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }
}