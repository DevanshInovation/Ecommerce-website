package com.ecm.modules.card.application;

import com.ecm.modules.card.dto.CartSnapshot;

public interface CartService {

    CartSnapshot getMyCart(String jwt);

    void addToCart(String jwt, Long productId, int qty);
}
