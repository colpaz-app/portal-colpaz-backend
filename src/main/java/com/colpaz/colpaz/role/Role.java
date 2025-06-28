package com.colpaz.colpaz.role;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String name;

    @Column(name = "created_at")
    private Timestamp createdAt = Timestamp.from(Instant.now());

    @Column(name = "modified_at")
    private Timestamp modifiedAt = Timestamp.from(Instant.now());

    @Column(name = "created_by", length = 100)
    private String createdBy;
}