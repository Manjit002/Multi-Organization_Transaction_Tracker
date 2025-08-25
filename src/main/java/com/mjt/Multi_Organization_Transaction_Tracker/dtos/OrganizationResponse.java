package com.mjt.Multi_Organization_Transaction_Tracker.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationResponse {
    private Long id;
    private String name;
    private String description;
}
