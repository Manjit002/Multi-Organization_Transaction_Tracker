package com.mjt.Multi_Organization_Transaction_Tracker.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "organization")
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    private String  name;

    private String  description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntite owner;

    @Column(nullable = false, updatable = false)
    private Instant  createdAt;

    @Column(nullable = false)
    private Instant  updatedAt;

    @OneToMany(mappedBy = "organization", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Txn> transactions= new HashSet<>();

    @PrePersist
    void onCreate() {
        createdAt = updatedAt= Instant.now();
    }
    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }

}
