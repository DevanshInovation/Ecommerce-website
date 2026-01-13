package com.ecm.legacy.service;

import com.ecm.legacy.domain.USER_ROLE;
import com.ecm.legacy.request.LoginRequest;
import com.ecm.legacy.response.AuthResponse;
import com.ecm.legacy.response.SignupRequest;

public interface AuthService {
	
	void sentLoginOtp(String email, USER_ROLE role) throws Exception;
	String createUser(SignupRequest req) throws Exception;
	AuthResponse signing(LoginRequest req) throws Exception;

}
