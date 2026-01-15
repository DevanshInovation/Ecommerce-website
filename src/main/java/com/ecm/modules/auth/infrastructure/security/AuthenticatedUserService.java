package com.ecm.modules.auth.infrastructure.security;

import org.springframework.stereotype.Service;

import com.ecm.modules.user.application.UserService;
import com.ecm.modules.user.domain.UserAggregate;
import com.ecm.modules.user.domain.UserSnapshot;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticatedUserService {

    private final JwtProvider jwtProvider;
    private final UserService userService;

    public UserSnapshot getCurrentUser(String jwt) {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return userService.findByEmail(email);
    }
}
