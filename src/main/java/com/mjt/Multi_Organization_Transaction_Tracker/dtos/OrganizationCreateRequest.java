package com.mjt.Multi_Organization_Transaction_Tracker.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationCreateRequest {

    @NotBlank
    private String name;
    private String description;
}
