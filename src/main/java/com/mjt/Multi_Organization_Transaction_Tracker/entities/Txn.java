package com.mjt.Multi_Organization_Transaction_Tracker.entities;

import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "txn")
public class Txn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 16)
    private TxnType type;

    @Column(nullable = false, precision = 20, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private String item;

    @Column(nullable = false)
    private LocalDate date;

    private String party;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "org_id", nullable = false)
    private Organization organization;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @PrePersist
    void onCreate() {
        createdAt = updatedAt = Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }
}
