package com.ecm.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.model.User;
import com.ecm.repository.UserRepository;
import com.ecm.response.SignupRequest;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository userRepository;
	
	
	@PostMapping("/signup")
	public ResponseEntity<User> createUserHandler(@RequestBody SignupRequest req){
		User user = new User();
		user.setEmail(req.getEmail());
		user.setFullName(req.getFullName());
		
		User saveUser = userRepository.save(user);
		return ResponseEntity.ok(saveUser);
	}

}
