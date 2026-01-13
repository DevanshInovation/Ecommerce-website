package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long>{

	Coupon findByCode(String code);
}
