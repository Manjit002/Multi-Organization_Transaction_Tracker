package com.mjt.Multi_Organization_Transaction_Tracker.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class SignupRequest {
    @NotBlank
    String name;
    @Email
    @NotBlank
    String email;
    @Size(min=8, message="Password must be at least 8 chars")
    String password;
}
