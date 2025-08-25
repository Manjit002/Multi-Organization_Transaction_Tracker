package com.mjt.Multi_Organization_Transaction_Tracker.repo;

import com.mjt.Multi_Organization_Transaction_Tracker.entities.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrganizationRepo extends JpaRepository<Organization, Long> {

    List<Organization> findByOwnerId(Long ownerId);
    Optional<Organization> findByIdAndOwnerId(Long id, Long ownerId);

}