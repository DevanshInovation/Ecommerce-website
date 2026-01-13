package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.WishList;

public interface WishlistRepository extends JpaRepository<WishList, Long>{

	WishList findByUserId(Long userId);
}
