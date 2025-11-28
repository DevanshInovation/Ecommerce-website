package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Seller;

public interface SellerRepository extends JpaRepository<Seller, Long> {
	Seller findByEmail(String email);

}
