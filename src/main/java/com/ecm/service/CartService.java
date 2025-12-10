package com.ecm.service;

import com.ecm.model.Cart;
import com.ecm.model.CartItem;
import com.ecm.model.Product;
import com.ecm.model.User;

public interface CartService {

	public CartItem addCartItem(
			User user,
			Product product,
			String size, int quentity
			);
	public Cart findUSerCArt(User user);
}
