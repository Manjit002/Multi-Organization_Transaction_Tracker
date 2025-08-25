package com.mjt.Multi_Organization_Transaction_Tracker.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Email
    @NotBlank
    String email;
    @NotBlank
    String password;

}
