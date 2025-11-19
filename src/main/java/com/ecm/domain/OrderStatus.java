package com.ecm.domain;

import lombok.Data;

@Data
public enum OrderStatus {

	PENDING,
	PLACED,
	CONFIRMED,
	SHIPPED,
	DELIVERED,
	CANCELLED
}
