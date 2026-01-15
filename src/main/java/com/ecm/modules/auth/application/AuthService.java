package com.ecm.modules.auth.application;


import com.ecm.modules.auth.dto.AuthResponse;
import com.ecm.modules.auth.dto.LoginOtpRequest;
import com.ecm.modules.auth.dto.LoginRequest;
import com.ecm.modules.auth.dto.SignupRequest;

public interface AuthService {

    AuthResponse signup(SignupRequest request) throws Exception;

    void sendLoginOtp(LoginOtpRequest request) throws Exception;

    AuthResponse login(LoginRequest request) throws Exception;
}

