package com.ecm.legacy.service;

import java.util.Set;

import com.ecm.legacy.model.Order;
import com.ecm.legacy.model.PaymentOrder;
import com.ecm.legacy.model.User;
import com.razorpay.PaymentLink;
import com.razorpay.RazorpayException;

public interface PaymentService {

	PaymentOrder createOrder(User user, Set<Order> order);
	PaymentOrder getPaymentOrdrById(Long Id) throws Exception;
	PaymentOrder getPaymentOrderByPaymentId(String orderId) throws Exception;
	boolean proceedPaymentOrder(PaymentOrder paymentOrder, 
			                    String paymentId,
			                    String paymentLinkId
			                   ) throws RazorpayException;
	PaymentLink createRozerPayPaymentLink(User user, Long amount,
			                              Long orderId) throws RazorpayException;
}
