package com.mjt.Multi_Organization_Transaction_Tracker.controller;

import com.mjt.Multi_Organization_Transaction_Tracker.dtos.OrganizationCreateRequest;
import com.mjt.Multi_Organization_Transaction_Tracker.dtos.OrganizationResponse;
import com.mjt.Multi_Organization_Transaction_Tracker.entities.Organization;
import com.mjt.Multi_Organization_Transaction_Tracker.services.OrganizationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orgs")
@RequiredArgsConstructor
public class OrganizationController {

    private final OrganizationService service;

    @PostMapping
    public OrganizationResponse create(@AuthenticationPrincipal User user,
                                       @RequestBody @Valid OrganizationCreateRequest req) {
        Organization o = service.create(user.getUsername(), req.getName(), req.getDescription());
        return new OrganizationResponse(o.getId(), o.getName(), o.getDescription());
    }

    @GetMapping
    public List<OrganizationResponse> list(@AuthenticationPrincipal User user) {
        return service.list(user.getUsername()).stream()
                .map(o -> new OrganizationResponse(o.getId(), o.getName(), o.getDescription()))
                .toList();
    }

    @DeleteMapping("/{id}")
    public void delete(@AuthenticationPrincipal User user, @PathVariable Long id) {
        service.delete(user.getUsername(), id);
    }
}
