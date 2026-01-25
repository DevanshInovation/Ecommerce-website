package com.ecm.modules.coupon.domain;

public class CouponDiscount {

    private final CouponType type;
    private final double value;

    public CouponDiscount(CouponType type, double value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Discount must be positive");
        }
        this.type = type;
        this.value = value;
    }

    public CouponType type() {
        return type;
    }

    public double value() {
        return value;
    }
}
