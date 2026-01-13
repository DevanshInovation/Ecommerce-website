package com.ecm.legacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long>{

	List<Review> findByProductId(Long productId);
}
