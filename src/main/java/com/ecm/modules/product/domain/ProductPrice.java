package com.ecm.modules.product.domain;

import jakarta.persistence.Embeddable;

@Embeddable
public class ProductPrice {

    private double amount;

    protected ProductPrice() {}

    public ProductPrice(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }
}
