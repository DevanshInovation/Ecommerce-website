package com.ecm.modules.order.domain;

import lombok.Getter;

@Getter
public class OrderItem {

    private Long productId;
    private int quantity;
    private double priceAtPurchase;

    protected OrderItem() {
    }

    public OrderItem(Long productId, int quantity, double priceAtPurchase) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        this.productId = productId;
        this.quantity = quantity;
        this.priceAtPurchase = priceAtPurchase;
    }

    public double totalPrice() {
        return quantity * priceAtPurchase;
    }

    // getters only
}
