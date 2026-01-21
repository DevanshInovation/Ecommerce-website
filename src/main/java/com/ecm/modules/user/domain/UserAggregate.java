package com.ecm.modules.user.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "user_user")
@Getter
public class UserAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private USER_ROLE role;

    protected UserAggregate() {
        // JPA only
    }

    public UserAggregate(String email, String fullName, USER_ROLE role) {
        if (email == null || email.isBlank())
            throw new IllegalArgumentException("Email required");

        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Full name required");

        this.email = email;
        this.fullName = fullName;
        this.role = role;
    }
}
