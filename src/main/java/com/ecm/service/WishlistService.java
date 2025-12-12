package com.ecm.service;

import com.ecm.model.Product;
import com.ecm.model.User;
import com.ecm.model.WishList;

public interface WishlistService {
	
	  WishList createWishlist(User user);

	    WishList getWishlistByUserId(User user);

	    WishList addProductToWishlist(User user, Product product);

}
