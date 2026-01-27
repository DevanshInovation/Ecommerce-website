package com.ecm.modules.wishlist.domain;

public class WishlistItem {

    private final Long productId;

    public WishlistItem(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }
}

