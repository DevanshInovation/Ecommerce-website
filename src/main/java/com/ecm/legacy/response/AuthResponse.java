package com.ecm.legacy.response;

import com.ecm.legacy.domain.USER_ROLE;

import lombok.Data;

@Data
public class AuthResponse {

	private String jwt;
	private String message;;
	private USER_ROLE role;
}
