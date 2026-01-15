package com.ecm.modules.auth.application;

import org.springframework.stereotype.Service;

import com.ecm.modules.auth.dto.AuthResponse;
import com.ecm.modules.auth.dto.LoginOtpRequest;
import com.ecm.modules.auth.dto.LoginRequest;
import com.ecm.modules.auth.dto.SignupRequest;
import com.ecm.modules.auth.infrastructure.otp.OtpService;
import com.ecm.modules.auth.infrastructure.security.JwtProvider;
import com.ecm.modules.user.application.UserService;
import com.ecm.modules.user.domain.UserSnapshot;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;      // ✅ only dependency
    private final OtpService otpService;        // ✅ infra
    private final JwtProvider jwtProvider;      // ✅ infra

    @Override
    public AuthResponse signup(SignupRequest req) {

        otpService.verifyOtp(req.getEmail(), req.getOtp());

        UserSnapshot user = userService.createUser(
                req.getEmail(),
                req.getFullName()
        );

        String jwt = jwtProvider.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(jwt, "Register success", user.getRole());
    }

    public void sendLoginOtp(LoginOtpRequest request) {
        otpService.sendOtp(request.getEmail(), request.getRole().name());
    }

    @Override
    public AuthResponse login(LoginRequest request) throws Exception {

        otpService.verifyOtp(request.getEmail(), request.getOtp());

        UserSnapshot user = userService.findByEmail(request.getEmail());

        String jwt = jwtProvider.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(jwt, "Login success", user.getRole());
    }
}

