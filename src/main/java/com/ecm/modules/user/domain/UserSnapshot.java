package com.ecm.modules.user.domain;

import com.ecm.modules.user.domain.USER_ROLE;
import lombok.*;

@Getter
@AllArgsConstructor
public class UserSnapshot {
    private Long id;
    private String email;
    private USER_ROLE role;
}