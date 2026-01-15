package com.ecm.modules.user.application;

import org.springframework.stereotype.Service;

import com.ecm.modules.user.domain.USER_ROLE;
import com.ecm.modules.user.domain.UserAggregate;
import com.ecm.modules.user.domain.UserSnapshot;
import com.ecm.modules.user.infrastructure.persistence.UserJpaRepository;
import com.ecm.modules.auth.infrastructure.security.JwtProvider;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserJpaRepository userJpaRepository;
    private final JwtProvider jwtProvider;

    @Override
    public UserSnapshot findByEmail(String email) {
        UserAggregate user = userJpaRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return toSnapshot(user);
    }

    @Override
    public UserSnapshot findByJwt(String jwt) {
        String email = jwtProvider.getEmailFromJwtToken(jwt);
        return findByEmail(email);
    }

    @Override
    public UserSnapshot createUser(String email, String fullName) {
        UserAggregate user = new UserAggregate();
        user.setEmail(email);
        user.setFullName(fullName);
        user.setRole(USER_ROLE.ROLE_CUSTOMER);

        UserAggregate saved = userJpaRepository.save(user);
        return toSnapshot(saved);
    }

    private UserSnapshot toSnapshot(UserAggregate user) {
        return new UserSnapshot(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );
    }
}