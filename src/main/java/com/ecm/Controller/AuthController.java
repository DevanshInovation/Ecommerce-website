package com.ecm.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.domain.USER_ROLE;
import com.ecm.model.User;
import com.ecm.model.VerificationCode;
import com.ecm.repository.UserRepository;
import com.ecm.request.LoginRequest;
import com.ecm.response.ApiResponse;
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
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody SignupRequest req) throws Exception{
		
		String jwt=authService.createUser(req);
		
		AuthResponse res=new AuthResponse();
		res.setJwt(jwt);
		res.setMessage("Register success");
		res.setRole(USER_ROLE.ROLE_CUSTOMER);
		
		return ResponseEntity.ok(res);
	}
	
	
	@PostMapping("/sent/login-signup-otp")
	public ResponseEntity<ApiResponse> sentOtprHandler(@RequestBody VerificationCode req) throws Exception{
		
		authService.sentLoginOtp(req.getEmail());
		
		ApiResponse res=new ApiResponse();
		
		res.setMessage("otp sent successfully");
	
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginHandler(@RequestBody LoginRequest req) throws Exception{
		
		AuthResponse authResponse=authService.signing(req);
		
		return ResponseEntity.ok(authResponse);
	}


}
