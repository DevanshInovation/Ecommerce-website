package com.ecm.legacy.service;

import com.ecm.legacy.model.Product;
import com.ecm.legacy.model.User;
import com.ecm.legacy.model.WishList;

public interface WishlistService {
	
	  WishList createWishlist(User user);

	    WishList getWishlistByUserId(User user);

	    WishList addProductToWishlist(User user, Product product);

}
