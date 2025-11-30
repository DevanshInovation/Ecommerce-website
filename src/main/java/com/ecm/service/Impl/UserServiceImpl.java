package com.ecm.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecm.config.JwtProvider;
import com.ecm.model.User;
import com.ecm.repository.UserRepository;
import com.ecm.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	
	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;
	
	@Override
	public User findUserByJwtToken(String jwt) throws Exception  {
		
		String email=jwtProvider.getEmailFromJwtToken(jwt);
		
		return this.findUSerByEmail(email);
	}

	@Override
	public User findUSerByEmail(String email) throws Exception {
		User user = userRepository.findByEmail(email);
		if(user==null) {
			throw new Exception("user not fount with this email - "+email);
		}
		return user;
	}

}
