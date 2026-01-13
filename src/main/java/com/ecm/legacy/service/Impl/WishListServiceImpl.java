package com.ecm.legacy.service.Impl;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecm.legacy.model.Product;
import com.ecm.legacy.model.User;
import com.ecm.legacy.model.WishList;
import com.ecm.legacy.repository.WishlistRepository;
import com.ecm.legacy.service.WishlistService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WishListServiceImpl implements WishlistService{

	private final WishlistRepository wishlistRepository;

	@Override
	public WishList createWishlist(User user) {
		WishList wishList=new WishList();
		wishList.setUser(user);
		return wishlistRepository.save(wishList);
	}

	@Override
	public WishList getWishlistByUserId(User user) {
		WishList wishList=wishlistRepository.findByUserId(user.getId());
		if(wishList==null) {
			wishList=createWishlist(user);
		}
		return wishList;
	}

	@Override
	public WishList addProductToWishlist(User user, Product product) {

	    WishList wishlist = getWishlistByUserId(user);

	    if (wishlist.getProducts().contains(product)) {
	        wishlist.getProducts().remove(product);
	    } else {
	        wishlist.getProducts().add(product);
	    }

	    return wishlistRepository.save(wishlist);
	}

}
