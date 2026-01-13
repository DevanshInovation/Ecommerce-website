package com.ecm.legacy.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecm.legacy.exception.ProductException;
import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.CartItem;
import com.ecm.legacy.model.Product;
import com.ecm.legacy.model.User;
import com.ecm.legacy.request.AddItemRequest;
import com.ecm.legacy.response.ApiResponse;
import com.ecm.legacy.service.CartItemService;
import com.ecm.legacy.service.CartService;
import com.ecm.legacy.service.ProductService;
import com.ecm.legacy.service.UserService;
import com.ecm.legacy.service.Impl.CartItemServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/cart")
public class CartController {

	private final CartItemService cartItemService;
	private final CartService cartService;
	private final UserService userService;
	private final ProductService productService;
	
	 @GetMapping
	    public ResponseEntity<Cart> findUserCartHandler(
	            @RequestHeader("Authorization") String jwt) throws Exception {

	        User user = userService.findUserByJwtToken(jwt);

	        Cart cart = cartService.findUSerCArt(user);

	        return new ResponseEntity<>(cart, HttpStatus.OK);
	    }
	 
	 @PutMapping("/add")
	 public ResponseEntity<CartItem> addItemToCart(
	         @RequestBody AddItemRequest req,
	         @RequestHeader("Authorization") String jwt
	 ) throws ProductException, Exception {

	     User user = userService.findUserByJwtToken(jwt);

	     Product product = productService.findProductById(req.getProductId());

	     CartItem item = cartService.addCartItem(
	             user,
	             product,
	             req.getSize(),
	             req.getQuentity()
	     );

	     ApiResponse res = new ApiResponse();
	     res.setMessage("Item added to cart successfully");

	     return new ResponseEntity<>(item, HttpStatus.ACCEPTED);
	 }

	 @DeleteMapping("/item/{cartItemId}")
	 public ResponseEntity<ApiResponse> deleteCartItemHandler(
	         @PathVariable Long cartItemId,
	         @RequestHeader("Authorization") String jwt
	 ) throws Exception {

	     User user = userService.findUserByJwtToken(jwt);

	     cartItemService.removeCartItem(user.getId(), cartItemId);

	     ApiResponse res = new ApiResponse();
	     res.setMessage("Item removed from cart");

	     return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
	 }

	 @PutMapping("/item/{cartItemId}")
	 public ResponseEntity<CartItem> updateCartItemHandler(
	         @PathVariable Long cartItemId,
	         @RequestBody CartItem cartItem,
	         @RequestHeader("Authorization") String jwt
	 ) throws Exception {

	     User user = userService.findUserByJwtToken(jwt);

	     CartItem updatedCartItem = null;
	     if (cartItem.getQuantity() > 0) {
	         updatedCartItem = cartItemService.updateCartItem(
	                 user.getId(),
	                 cartItemId,
	                 cartItem
	         );
	     }

	     return new ResponseEntity<>(updatedCartItem, HttpStatus.ACCEPTED);
	 }
 
}
