package com.ecm.Controller;

import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.domain.PaymentMethod;
import com.ecm.model.Address;
import com.ecm.model.Cart;
import com.ecm.model.Order;
import com.ecm.model.OrderItem;
import com.ecm.model.User;
import com.ecm.response.PaymentLinkResponse;
import com.ecm.service.CartService;
import com.ecm.service.OrderService;
import com.ecm.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;
	private final UserService userService;
	private final CartService cartService;
	
	@PostMapping("/orders")
	public ResponseEntity<PaymentLinkResponse> createOrderHandler(
	        @RequestBody Address shippingAddress,
	        @RequestParam PaymentMethod paymentMethod,
	        @RequestHeader("Authorization") String jwt
	) throws Exception {

	    // 1. Current user & cart
	    User user = userService.findUserByJwtToken(jwt);
	    Cart cart = cartService.findUSerCArt(user);

	    // 2. Create multi-seller orders
	    Set<Order> orders = orderService.createOrder(user, shippingAddress, cart);

//	    // 3. Create PaymentOrder (your own table)
//	    PaymentOrder paymentOrder = paymentService.createOrder(user, orders);
//
	    PaymentLinkResponse res = new PaymentLinkResponse();

	    // 4. Razorpay payment link (if online payment)
//	    if (paymentMethod.equals(PaymentMethod.RAZORPAY)) {
//
//	        Map<String, Object> payment =
//	                paymentService.createRazorpayPaymentLink(
//	                        user,
//	                        paymentOrder.getAmount(),
//	                        paymentOrder.getId()
//	                );
//
//	        String paymentUrl = (String) payment.get("short_url");
//	        String paymentUrlId = (String) payment.get("id");
//
//	        res.setPayment_url(paymentUrl);
//	        res.setPayment_link_id(paymentUrlId);
//	    }
//
//	    res.setPaymentOrderId(paymentOrder.getId());
//	    res.setMessage("Order created successfully");

	    return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// GET /api/orders/{orderId}
	@GetMapping("/{orderId}")
	public ResponseEntity<Order> getOrderById(
	        @PathVariable Long orderId,
	        @RequestHeader("Authorization") String jwt
	) throws Exception {

	    User user = userService.findUserByJwtToken(jwt);

	    Order order = orderService.findOrderById(orderId);

	    // optional security check
	    if (!user.getId().equals(order.getUserId().getId())) {
	        throw new Exception("You don't have access to this order");
	    }

	    return new ResponseEntity<>(order, HttpStatus.ACCEPTED);
	}
	
	// GET /api/orders/item/{orderItemId}
	@GetMapping("/item/{orderItemId}")
	public ResponseEntity<OrderItem> getOrderItemById(
	        @PathVariable Long orderItemId,
	        @RequestHeader("Authorization") String jwt
	) throws Exception {

	    System.out.println("------- controller ");

	    User user = userService.findUserByJwtToken(jwt);

	    OrderItem orderItem = orderService.findOrderItemById(orderItemId);

	    // optional security check
	    if (!user.getId().equals(orderItem.getUserId())) {
	        throw new Exception("You don't have access to this order item");
	    }

	    return new ResponseEntity<>(orderItem, HttpStatus.ACCEPTED);
	}


	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cancelOrder(
	        @PathVariable Long orderId,
	        @RequestHeader("Authorization") String jwt
	) throws Exception {

	    User user = userService.findUserByJwtToken(jwt);

	    // 1. Order cancel (status change, user check etc.)
	    Order order = orderService.cancelOrder(orderId, user);

//	    // 2. Seller report update
//	    Seller seller = sellerService.getSellerById(order.getSellerId());
//	    SellerReport report = sellerReportService.getSellerReport(seller);
//
//	    report.setCanceledOrders(report.getCanceledOrders() + 1);
//	    report.setTotalRefunds(
//	            report.getTotalRefunds() + order.getTotalSellingPrice()
//	    );

//	    sellerReportService.updateSellerReport(report);

	    return ResponseEntity.ok(order);
	}


}
