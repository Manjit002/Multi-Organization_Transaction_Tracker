package com.mjt.Multi_Organization_Transaction_Tracker.repo;

import com.mjt.Multi_Organization_Transaction_Tracker.entities.UserEntite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntite, Long> {
    Optional<UserEntite> findByEmail(String email);

    boolean existsByEmail(String email);
}
