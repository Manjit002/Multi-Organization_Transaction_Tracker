package com.mjt.Multi_Organization_Transaction_Tracker.dtos;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GoogleLoginRequest {
    @NotBlank
    @JsonProperty("idToken")
    @JsonAlias({"id_token"})
    @Schema(description = "Google ID token (JWT) from OAuth Playground or your client")
    private String idToken;
}
