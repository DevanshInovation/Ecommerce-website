package com.ecm.legacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.domain.AccountStatus;
import com.ecm.legacy.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
	Seller findByEmail(String email);
	List<Seller> findByAccountStatus(AccountStatus status);

}
