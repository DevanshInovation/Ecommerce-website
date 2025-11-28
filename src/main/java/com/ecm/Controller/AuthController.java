package com.ecm.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.domain.USER_ROLE;
import com.ecm.model.User;
import com.ecm.repository.UserRepository;
import com.ecm.response.AuthResponse;
import com.ecm.response.SignupRequest;
import com.ecm.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
	private final UserRepository userRepository;
	private final AuthService authService;
	
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req){
		
		String jwt=authService.createUser(req);
		
		AuthResponse res=new AuthResponse();
		res.setJwt(jwt);
		res.setMessage("Register success");
		res.setRole(USER_ROLE.ROLE_CUSTOMER);
		
		return ResponseEntity.ok(res);
	}

}
