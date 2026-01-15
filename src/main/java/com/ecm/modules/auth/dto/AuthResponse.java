package com.ecm.modules.auth.dto;

import com.ecm.legacy.domain.USER_ROLE;
import lombok.Data;

@Data
public class AuthResponse {

	private String jwt;
	private String message;;
	private USER_ROLE role;
	
	public AuthResponse(
	        String token,
	        String message,
	        com.ecm.modules.user.domain.USER_ROLE user_ROLE
	) {}

}
