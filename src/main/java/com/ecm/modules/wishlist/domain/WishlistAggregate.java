package com.ecm.modules.wishlist.domain;

import java.util.HashSet;
import java.util.Set;

public class WishlistAggregate {

    private final Long userId;
    private final Set<WishlistItem> items = new HashSet<>();

    private WishlistAggregate(Long userId) {
        this.userId = userId;
    }

    public static WishlistAggregate create(Long userId) {
        return new WishlistAggregate(userId);
    }

    public void addProduct(Long productId) {
        items.add(new WishlistItem(productId));
    }

    public void removeProduct(Long productId) {
        items.removeIf(i -> i.getProductId().equals(productId));
    }

    public Set<WishlistItem> getItems() {
        return items;
    }
}

