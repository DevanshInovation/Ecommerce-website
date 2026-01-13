package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.CartItem;
import com.ecm.legacy.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);

}
