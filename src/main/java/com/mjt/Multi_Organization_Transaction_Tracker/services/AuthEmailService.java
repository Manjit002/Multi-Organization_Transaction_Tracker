package com.mjt.Multi_Organization_Transaction_Tracker.services;


import com.mjt.Multi_Organization_Transaction_Tracker.dtos.JwtResponse;
import com.mjt.Multi_Organization_Transaction_Tracker.dtos.LoginRequest;
import com.mjt.Multi_Organization_Transaction_Tracker.dtos.SignupRequest;
import com.mjt.Multi_Organization_Transaction_Tracker.entities.UserEntite;
import com.mjt.Multi_Organization_Transaction_Tracker.repo.UserRepo;
import com.mjt.Multi_Organization_Transaction_Tracker.security.jwt.JwtService;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthEmailService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final UserService userService; // your existing UserDetailsService
    private final JwtService jwtService;

    @Transactional
    public JwtResponse signup(SignupRequest req) {
        String email = req.getEmail().trim().toLowerCase();
        if (userRepo.existsByEmail(email)) {
            throw new IllegalArgumentException("Email already in use");
        }
        UserEntite u = new UserEntite();
        u.setName(req.getName());
        u.setEmail(email);
        u.setPassword(passwordEncoder.encode(req.getPassword()));
        // pictureUrl optional; createdAt/updatedAt via @PrePersist
        userRepo.save(u);

        var ud = userService.loadUserByUsername(email);
        String token = jwtService.generate(
                Map.of("uid", u.getId(), "name", u.getName()),
                ud
        );
        return new JwtResponse(token);
    }

    @Transactional(readOnly = true)
    public JwtResponse login(LoginRequest req) {
        String email = req.getEmail().trim().toLowerCase();
        UserEntite u = userRepo.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid credentials"));

        // handle Google-only accounts with no password set
        if (u.getPassword() == null || !passwordEncoder.matches(req.getPassword(), u.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        var ud = userService.loadUserByUsername(email);
        String token = jwtService.generate(
                Map.of("uid", u.getId(), "name", u.getName()),
                ud
        );
        return new JwtResponse(token);
    }
}
