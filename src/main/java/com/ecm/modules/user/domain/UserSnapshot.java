package com.ecm.modules.user.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSnapshot {
    private Long id;
    private String email;
    private String fullName;
    private USER_ROLE role;
}
