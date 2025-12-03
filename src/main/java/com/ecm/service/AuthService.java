package com.ecm.service;

import com.ecm.domain.USER_ROLE;
import com.ecm.request.LoginRequest;
import com.ecm.response.AuthResponse;
import com.ecm.response.SignupRequest;

public interface AuthService {
	
	void sentLoginOtp(String email, USER_ROLE role) throws Exception;
	String createUser(SignupRequest req) throws Exception;
	AuthResponse signing(LoginRequest req) throws Exception;

}
