package com.dev.api_auth.models.entitites;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@MappedSuperclass
public abstract class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(nullable = false)
    private boolean isActive = Boolean.TRUE;
}
