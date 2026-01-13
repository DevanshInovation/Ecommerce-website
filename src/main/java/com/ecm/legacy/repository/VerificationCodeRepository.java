package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>{
	
	VerificationCode findByEmail(String email);
	VerificationCode findByOtp(String otp);

}
