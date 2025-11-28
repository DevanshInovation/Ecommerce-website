package com.ecm.service;

import com.ecm.response.SignupRequest;

public interface AuthService {
	
	String createUser(SignupRequest req);

}
