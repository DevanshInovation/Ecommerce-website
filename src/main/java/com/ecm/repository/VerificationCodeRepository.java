package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.VerificationCode;

public interface VerificationCodeRepository extends JpaRepository<VerificationCode, Long>{
	
	VerificationCode findByEmail(String email);
	

}
