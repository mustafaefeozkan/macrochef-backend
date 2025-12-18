package com.macrochef.service;

import com.macrochef.dto.AuthResponse;
import com.macrochef.dto.LoginRequest;
import com.macrochef.dto.RegisterRequest;
import com.macrochef.entity.Role;
import com.macrochef.entity.User;
import com.macrochef.repository.RoleRepository;
import com.macrochef.repository.UserRepository;
import com.macrochef.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // --------------------
    // REGISTER
    // --------------------
    public AuthResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("ROLE_USER not found"));

        user.getRoles().add(userRole);
        userRepository.save(user);

        String token = jwtUtil.generateToken(user.getId());

        return new AuthResponse("Registration successful", token);
    }

    // --------------------
    // LOGIN
    // --------------------
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getId());

        return new AuthResponse("Login successful", token);
    }
}
