package com.ecm.legacy.service;

import com.ecm.legacy.model.User;

public interface UserService {

	User findUserByJwtToken(String jwt) throws Exception;
	User findUSerByEmail(String email) throws Exception;
}
