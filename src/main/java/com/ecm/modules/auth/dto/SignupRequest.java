package com.ecm.modules.auth.dto;

import lombok.Data;

@Data
public class SignupRequest {

	private String email;
	private String fullName;
	private String otp;
	
}
