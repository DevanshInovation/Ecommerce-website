package com.ecm.modules.seller.domain;

public class SellerRating {

    private final double value;

    public SellerRating(double value) {
        if (value < 0.0 || value > 5.0) {
            throw new IllegalArgumentException("Seller rating must be between 0 and 5");
        }
        this.value = value;
    }

    public double value() {
        return value;
    }
}

