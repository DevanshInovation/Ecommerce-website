package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
}
