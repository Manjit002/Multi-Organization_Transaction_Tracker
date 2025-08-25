package com.mjt.Multi_Organization_Transaction_Tracker.controller;

import com.mjt.Multi_Organization_Transaction_Tracker.dtos.GoogleLoginRequest;
import com.mjt.Multi_Organization_Transaction_Tracker.dtos.JwtResponse;
import com.mjt.Multi_Organization_Transaction_Tracker.dtos.LoginRequest;
import com.mjt.Multi_Organization_Transaction_Tracker.dtos.SignupRequest;
import com.mjt.Multi_Organization_Transaction_Tracker.entities.UserEntite;
import com.mjt.Multi_Organization_Transaction_Tracker.security.GoogleTokenVerifier;
import com.mjt.Multi_Organization_Transaction_Tracker.security.jwt.JwtService;
import com.mjt.Multi_Organization_Transaction_Tracker.services.AuthEmailService;
import com.mjt.Multi_Organization_Transaction_Tracker.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final GoogleTokenVerifier googleTokenVerifier;
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthEmailService authEmailService;

    @Value("${app.oauth2.google.clientId}")
    private String googleClientId;

    @PostMapping("/google")
    public JwtResponse loginWithGoogle(@RequestBody @Valid GoogleLoginRequest req) {
        Jwt googleJwt = googleTokenVerifier.verify(req.getIdToken(), googleClientId);

        // TEMP: log minimal claims
        System.out.println("aud=" + googleJwt.getAudience() + ", iss=" + googleJwt.getIssuer() +
                ", sub=" + googleJwt.getSubject() + ", email=" + googleJwt.getClaimAsString("email"));

        String email = googleJwt.getClaimAsString("email");
        String name = googleJwt.getClaimAsString("name");
        String picture = googleJwt.getClaimAsString("picture");

        UserEntite u = userService.getOrCreate(email, name, picture);
        String token = jwtService.generate(
                Map.of("uid", u.getId(), "name", u.getName()),
                userService.loadUserByUsername(email)
        );
        return new JwtResponse(token);
    }


    @PostMapping("/signup")
    public JwtResponse signup(@RequestBody @Valid SignupRequest req) {
        return authEmailService.signup(req);
    }

    @PostMapping("/login")
    public JwtResponse login(@RequestBody @Valid LoginRequest req) {
        return authEmailService.login(req);
    }
}
