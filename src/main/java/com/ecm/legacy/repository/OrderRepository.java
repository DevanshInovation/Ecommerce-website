package com.ecm.legacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	// Find orders by the user's id (userId is a User entity in Order)
	List<Order> findByUserId_Id(Long userId);
	List<Order> findBySellerId(Long sellerId);

}
