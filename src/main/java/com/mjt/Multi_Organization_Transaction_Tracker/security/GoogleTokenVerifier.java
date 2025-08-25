package com.mjt.Multi_Organization_Transaction_Tracker.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtValidationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class GoogleTokenVerifier {
    private final @Qualifier("googleJwtDecoder") JwtDecoder decoder;

    public Jwt verify(String idToken, String clientId) {
        Jwt jwt = decoder.decode(idToken); // signature + exp + issuer already checked
        if (jwt.getAudience() == null || !jwt.getAudience().contains(clientId)) {
            throw new JwtException("Invalid audience (aud) for clientId: " + clientId);
        }
        Boolean emailVerified = jwt.getClaim("email_verified");
        if (emailVerified != null && !emailVerified) {
            throw new JwtException("Email not verified by Google");
        }
        return jwt;
    }
}