package com.ecm.modules.auth.infrastructure.otp;

import org.springframework.stereotype.Service;

@Service
public class OtpService {

    public void verifyOtp(String email, String otp) {
        // TODO: legacy OTP logic temporarily here
        // later move to proper OTP module
    }

    public void sendOtp(String email, String role) {
        // send OTP
    }
}
