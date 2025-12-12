package com.ecm.service;

import java.util.List;

import com.ecm.model.Product;
import com.ecm.model.Review;
import com.ecm.model.User;
import com.ecm.request.CreateReviewRequest;

public interface ReviewService {

	Review createReview(CreateReviewRequest req, User user, Product product);
	List<Review> getReviewByProductId(Long productId);
	Review updateReview(Long reviewId, String reviewText, double rating, Long userId) throws Exception;
	void deleteReview(Long reviewId, Long useerId) throws Exception;
	Review getReviewById(Long reviewId) throws Exception;
}
