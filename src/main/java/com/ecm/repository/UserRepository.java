package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

	User findByEmail(String email);
	
}
