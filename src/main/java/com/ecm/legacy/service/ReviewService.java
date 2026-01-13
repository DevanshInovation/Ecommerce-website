package com.ecm.legacy.service;

import java.util.List;

import com.ecm.legacy.model.Product;
import com.ecm.legacy.model.Review;
import com.ecm.legacy.model.User;
import com.ecm.legacy.request.CreateReviewRequest;

public interface ReviewService {

	Review createReview(CreateReviewRequest req, User user, Product product);
	List<Review> getReviewByProductId(Long productId);
	Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;
	void deleteReview(Long reviewId, Long useerId) throws Exception;
	Review getReviewById(Long reviewId) throws Exception;
}
