package com.ecm.legacy.request;

import lombok.Data;

@Data
public class AddItemRequest {

	private String size;
	private int quentity;
	private Long productId;
}
