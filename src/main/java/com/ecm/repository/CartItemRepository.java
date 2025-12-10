package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Cart;
import com.ecm.model.CartItem;
import com.ecm.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long>{

	CartItem findByCartAndProductAndSize(Cart cart, Product product, String size);

}
