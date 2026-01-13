package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder, Long>{
	
	PaymentOrder findByPaymentLinkId(String PaymentId);

}
