package com.ecm.legacy.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.legacy.model.Order;
import com.ecm.legacy.model.PaymentOrder;
import com.ecm.legacy.model.Seller;
import com.ecm.legacy.model.SellerReport;
import com.ecm.legacy.model.User;
import com.ecm.legacy.response.ApiResponse;
import com.ecm.legacy.response.PaymentLinkResponse;
import com.ecm.legacy.service.OrderService;
import com.ecm.legacy.service.PaymentService;
import com.ecm.legacy.service.SellerReportService;
import com.ecm.legacy.service.SellerService;
import com.ecm.legacy.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment")
public class PaymentController {

	private final PaymentService paymentService;
	private final UserService userService;
	private final SellerService sellerService;
	private OrderService orderService;
	private final SellerReportService sellerReportService;
	
	@GetMapping("/{paymentId}")
	public ResponseEntity<ApiResponse> paymentSuccessHandler(
	        @PathVariable String paymentId,
	        @RequestParam String paymentLinkId,
	        @RequestHeader("Authorization") String jwt
	) throws Exception {

	    User user = userService.findUserByJwtToken(jwt);

	    PaymentLinkResponse paymentResponse;

	    PaymentOrder paymentOrder = paymentService
	            .getPaymentOrderByPaymentId(paymentLinkId);

	    boolean paymentSuccess = paymentService.proceedPaymentOrder(
	            paymentOrder,
	            paymentId,
	            paymentLinkId
	    );

	    if (paymentSuccess) {
	        for (Order order : paymentOrder.getOrders()) {

	            Seller seller = sellerService.getSellerById(order.getSellerId());
	            SellerReport report = sellerReportService.getSellerReport(seller);
	            report.setTotalOrders(report.getTotalOrders() + 1);
	            report.setTotalEarnings(report.getTotalEarnings() + order.getTotalSellingPrice());
	            report.setTotalSales(report.getTotalSales() + order.getOrderItem().size());
	            sellerReportService.updateSellerReport(report);
	        }
	    }

	    ApiResponse res = new ApiResponse();
	    res.setMessage("Payment successful");
	 

	    return new ResponseEntity<>(res, HttpStatus.CREATED);
	}

	
}
