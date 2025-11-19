package com.ecm.domain;

import lombok.Data;

@Data
public enum PaymentStatus {

	PENDING,
	PROCESSING,
	COMPLETED,
	FAILED
}
