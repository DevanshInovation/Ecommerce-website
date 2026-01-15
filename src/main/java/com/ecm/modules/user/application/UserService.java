package com.ecm.modules.user.application;

import com.ecm.modules.user.domain.UserSnapshot;

public interface UserService {

    UserSnapshot findByEmail(String email);

    UserSnapshot findByJwt(String jwt);

    UserSnapshot createUser(String email, String fullName);
}