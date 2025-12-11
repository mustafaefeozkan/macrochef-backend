package com.macrochef.service;

import com.macrochef.dto.AuthResponse;
import com.macrochef.dto.LoginRequest;
import com.macrochef.dto.RegisterRequest;
import com.macrochef.entity.User;
import com.macrochef.repository.UserRepository;
import com.macrochef.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthResponse register(RegisterRequest request) {

        // email daha önce kullanılmış mı?
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return new AuthResponse("Email already exists", null);
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return new AuthResponse("Registration successful", null);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null || !passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse("Invalid credentials", null);
        }

        // JWT token üret
        String token = jwtUtil.generateToken(user.getId());

        return new AuthResponse("Login successful", token);
    }
}
