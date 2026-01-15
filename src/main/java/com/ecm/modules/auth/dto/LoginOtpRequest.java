package com.ecm.modules.auth.dto;

import com.ecm.modules.user.domain.USER_ROLE;

import lombok.Data;

@Data
public class LoginOtpRequest {
   private String email;
   private USER_ROLE role;

}
