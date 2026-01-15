package com.ecm.modules.auth.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.legacy.domain.USER_ROLE;
import com.ecm.legacy.request.LoginOtpRequest;
import com.ecm.legacy.request.LoginRequest;
import com.ecm.legacy.response.ApiResponse;
import com.ecm.legacy.response.AuthResponse;
import com.ecm.legacy.response.SignupRequest;
import com.ecm.legacy.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
	
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
	public ResponseEntity<ApiResponse> sentOtprHandler(@RequestBody LoginOtpRequest req) throws Exception{
		
		authService.sentLoginOtp(req.getEmail(), req.getRole());
		
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
