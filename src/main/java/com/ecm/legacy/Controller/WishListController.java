package com.ecm.legacy.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.legacy.model.User;
import com.ecm.legacy.model.WishList;
import com.ecm.legacy.service.ProductService;
import com.ecm.legacy.service.UserService;
import com.ecm.legacy.service.WishlistService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/Wishlist")
public class WishListController {

	private final WishlistService wishlistService;
	private final UserService userService;
	private final ProductService productService;
	
	@GetMapping()
	public ResponseEntity<WishList> getWishlistByUserId(
	        @RequestHeader("Authorization") String jwt) throws Exception {

	    User user = userService.findUserByJwtToken(jwt);

	    WishList wishlist = wishlistService.getWishlistByUserId(user);

	    return ResponseEntity.ok(wishlist);
	}

}
