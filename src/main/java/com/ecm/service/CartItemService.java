package com.ecm.service;

import com.ecm.model.CartItem;

public interface CartItemService {

	CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception;
	void removeCartItem(Long userId, Long cartItemId) throws Exception;
	CartItem findByCartItemId(Long id);
}
