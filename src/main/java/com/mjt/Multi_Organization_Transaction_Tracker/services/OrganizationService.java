package com.mjt.Multi_Organization_Transaction_Tracker.services;

import com.mjt.Multi_Organization_Transaction_Tracker.entities.Organization;
import com.mjt.Multi_Organization_Transaction_Tracker.entities.UserEntite;
import com.mjt.Multi_Organization_Transaction_Tracker.repo.OrganizationRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrganizationService {

    @Autowired
    private final OrganizationRepo repo;
    @Autowired
    private final UserService userService;

    public Organization create(String ownerEmail, String name, String description) {
        UserEntite owner = userService.byEmail(ownerEmail);
        Organization org = Organization.builder()
                .name(name)
                .description(description)
                .owner(owner)
                .build();
        return repo.save(org);
    }

    public List<Organization> list(String ownerEmail) {
        UserEntite owner = userService.byEmail(ownerEmail);
        return repo.findByOwnerId(owner.getId());
    }

    public Organization getOwned(String ownerEmail, Long id) {
        UserEntite owner = userService.byEmail(ownerEmail);
        return repo.findByIdAndOwnerId(id, owner.getId())
                .orElseThrow(() -> new RuntimeException("Organization not found"));
    }

    public void delete(String ownerEmail, Long id) {
        Organization org = getOwned(ownerEmail, id);
        repo.delete(org);
    }
}

