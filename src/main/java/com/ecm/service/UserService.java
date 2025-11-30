package com.ecm.service;

import com.ecm.model.User;

public interface UserService {

	User findUserByJwtToken(String jwt) throws Exception;
	User findUSerByEmail(String email) throws Exception;
}
