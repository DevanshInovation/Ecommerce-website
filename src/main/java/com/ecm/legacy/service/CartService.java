package com.ecm.legacy.service;

import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.CartItem;
import com.ecm.legacy.model.Product;
import com.ecm.legacy.model.User;

public interface CartService {

	public CartItem addCartItem(
			User user,
			Product product,
			String size, int quentity
			);
	public Cart findUSerCArt(User user);
}
