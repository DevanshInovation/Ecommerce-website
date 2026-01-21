package com.ecm.modules.inventory.domain;

public class StockQuantity {

    private final int value;

    public StockQuantity(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Stock cannot be negative");
        }
        this.value = value;
    }

    public StockQuantity increase(int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        return new StockQuantity(this.value + qty);
    }

    public StockQuantity decrease(int qty) {
        if (qty <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        if (this.value < qty) {
            throw new IllegalStateException("Insufficient stock");
        }
        return new StockQuantity(this.value - qty);
    }

    public boolean isEmpty() {
        return value == 0;
    }

    public int value() {
        return value;
    }
}

