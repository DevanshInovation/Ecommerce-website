package com.ecm.modules.user.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.User;
import com.ecm.modules.user.domain.UserAggregate;

public interface UserJpaRepository extends JpaRepository<UserAggregate, Long>{

	 Optional<UserAggregate> findByEmail(String email);
}