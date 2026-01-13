package com.ecm.legacy.service.Impl;

import org.springframework.stereotype.Service;

import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.CartItem;
import com.ecm.legacy.model.Product;
import com.ecm.legacy.model.User;
import com.ecm.legacy.repository.CartItemRepository;
import com.ecm.legacy.repository.CartRepository;
import com.ecm.legacy.service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

	private final CartItemRepository cartItemRepository;
	private final CartRepository cartRepository;
	
	@Override
	public CartItem addCartItem(User user, Product product, String size, int quentity) {
	   Cart cart=findUSerCArt(user);
	   
	   CartItem isPressent=cartItemRepository.findByCartAndProductAndSize(cart, product, size);

	    if (isPressent == null) {
	        CartItem cartItem = new CartItem();
	        cartItem.setProduct(product);
	        cartItem.setQuantity(quentity);
	        cartItem.setUserId(user.getId());
	        cartItem.setSize(size);

	        int totalPrice = quentity * product.getSellingPrice();
	        cartItem.setSellingPrice(totalPrice);
	        cartItem.setMrpPrice(quentity * product.getMrpPrice());

	        cart.getCartItems().add(cartItem);
	        cartItem.setCart(cart);

	        return cartItemRepository.save(cartItem);
	    }

		return isPressent;
	}
	@Override
	public Cart findUSerCArt(User user) {
		Cart cart=cartRepository.findByUserId(user.getId());
		
		int totalPrice=0;
		int totaliDscountPrice=0;
		int totalItem=0;
			
			 for (CartItem cartItem : cart.getCartItems()) {
			        totalPrice += cartItem.getMrpPrice() * cartItem.getQuantity();
			        totaliDscountPrice += cartItem.getSellingPrice() * cartItem.getQuantity();
			        totalItem += cartItem.getQuantity();
			    }

			    cart.setTotalMrpPrice(totalPrice);
			    cart.setTotalSellingPrice(totaliDscountPrice);
			    cart.setTotalItem(totalItem);
			    cart.setDiscount(calculateDiscountPercentage(totaliDscountPrice, totalPrice));
			    cart.setTotalItem(totalItem);

			    return cart;
			}
	
			private int calculateDiscountPercentage(int mrpPrice, int sellingPrice) {
			    if (mrpPrice <= 0) {
			        return 0;
			    }
			    int discount = mrpPrice - sellingPrice;
			    double fraction = (double) discount / mrpPrice;
			    return (int) Math.round(fraction * 100);
			}
	
}
