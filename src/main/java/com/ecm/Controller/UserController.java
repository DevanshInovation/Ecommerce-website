package com.ecm.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.domain.USER_ROLE;
import com.ecm.model.User;
import com.ecm.repository.UserRepository;
import com.ecm.response.AuthResponse;
import com.ecm.response.SignupRequest;
import com.ecm.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	
	@GetMapping("/user/profile")
	public ResponseEntity<User> createUserHandler(@RequestHeader("Authorization") String jwt) throws Exception{
		
		User user = userService.findUserByJwtToken(jwt);
		
		return ResponseEntity.ok(user);
	}
}
