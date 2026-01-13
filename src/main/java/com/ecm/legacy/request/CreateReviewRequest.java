package com.ecm.legacy.request;

import java.util.List;

import lombok.Data;

@Data
public class CreateReviewRequest {

	private String reviewText;
	private double reviewRatings;
	private List<String> productImages;
}
