package com.mjt.Multi_Organization_Transaction_Tracker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "app_user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserEntite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String  name;

    @Column(unique = true, nullable = false)
    private String email;

//    @Column(nullable = false)
    private String password;

    private String pictureUrl;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Organization> organizations= new HashSet<>();

    @PrePersist
    void onCreate() {
        createdAt = updatedAt = Instant.now();
    }
    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }
}
