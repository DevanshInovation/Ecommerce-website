package com.ecm.service.Impl;

import org.springframework.stereotype.Service;

import com.ecm.model.CartItem;
import com.ecm.model.User;
import com.ecm.repository.CartItemRepository;
import com.ecm.service.CartItemService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService{

	private final CartItemRepository cartItemRepository;
	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws Exception {
		 CartItem item = findCartItemById(id);

		    User cartItemUser = item.getCart().getUser();

		    if (cartItemUser.getId().equals(userId)) {

		        item.setQuantity(cartItem.getQuantity());
		        item.setMrpPrice(cartItem.getQuantity()*item.getProduct().getMrpPrice());
		        item.setSellingPrice(cartItem.getQuantity()*item.getProduct().getSellingPrice());
		        return cartItemRepository.save(item);
		    }

		    throw new Exception("You can't update this cartItem");
	}

	private CartItem findCartItemById(Long id) throws Exception {
		return cartItemRepository.findById(id).orElseThrow(()->
		new Exception("cart item not found with id "+ id));
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws Exception {
		CartItem item = findCartItemById(cartItemId);

	    User cartItemUser = item.getCart().getUser();

	    if (cartItemUser.getId().equals(userId)) {
	        cartItemRepository.delete(item);
	    } else {
	        throw new Exception("You can't delete this cart item");
	    }
		
	}

	@Override
	public CartItem findByCartItemId(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
