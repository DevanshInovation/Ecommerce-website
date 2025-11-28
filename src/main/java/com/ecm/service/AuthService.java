package com.ecm.service;

import com.ecm.response.SignupRequest;

public interface AuthService {
	
	void sentLoginOtp(String email) throws Exception;
	String createUser(SignupRequest req) throws Exception;

}
