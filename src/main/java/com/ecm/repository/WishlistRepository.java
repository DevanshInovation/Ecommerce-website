package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.WishList;

public interface WishlistRepository extends JpaRepository<WishList, Long>{

	WishList findByUserId(Long userId);
}
