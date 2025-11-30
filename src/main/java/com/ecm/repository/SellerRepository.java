package com.ecm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.domain.AccountStatus;
import com.ecm.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
	Seller findByEmail(String email);
	List<Seller> findByAccountStatus(AccountStatus status);

}
