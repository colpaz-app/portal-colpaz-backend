package com.colpaz.colpaz.userAccount;

import com.colpaz.colpaz.role.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.Instant;

@Setter
@Getter
@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(name = "is_active")
    private boolean isActive = true;

    @Column(name = "created_at")
    private Timestamp createdAt = Timestamp.from(Instant.now());

    @Column(name = "modified_at")
    private Timestamp modifiedAt = Timestamp.from(Instant.now());

    @Column(name = "created_by", length = 100)
    private String createdBy;
}