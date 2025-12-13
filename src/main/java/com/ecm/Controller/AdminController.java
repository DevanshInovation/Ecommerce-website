package com.ecm.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.domain.AccountStatus;
import com.ecm.model.Seller;
import com.ecm.service.SellerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class AdminController {

	private final SellerService sellerService;
	
	@PatchMapping("/seller/{id}/status/{status}")
	public ResponseEntity<Seller> updateSellerStatus(
	        @PathVariable Long id,
	        @PathVariable AccountStatus status) throws Exception {

	    Seller updatedSeller = sellerService.updateSellerAccountStatus(id, status);
	    return ResponseEntity.ok(updatedSeller);
	}

}
