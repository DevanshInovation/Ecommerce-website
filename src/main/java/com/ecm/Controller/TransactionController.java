package com.ecm.Controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.model.Seller;
import com.ecm.model.Transaction;
import com.ecm.service.SellerService;
import com.ecm.service.TransactionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/transactions")
public class TransactionController {

	private final TransactionService transactionService;
	private final SellerService sellerService;
	
	 @GetMapping("/seller")
	    public ResponseEntity<List<Transaction>> getTransactionBySeller(
	            @RequestHeader("Authorization") String jwt) throws Exception {

	        Seller seller = sellerService.getSellerProfile(jwt);

	        List<Transaction> transactions =
	                transactionService.getTransactionBySellerId(seller);

	        return ResponseEntity.ok(transactions);
	    }

	 @GetMapping
	    public ResponseEntity<List<Transaction>> getAllTransactions() {

	        List<Transaction> transactions =
	                transactionService.getAllTransaction();

	        return ResponseEntity.ok(transactions);
	    }
}
