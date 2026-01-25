package com.ecm.modules.coupon.domain;

import java.util.Objects;

public class CouponCode {

    private final String value;

    public CouponCode(String value) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Coupon code cannot be empty");
        }
        this.value = value.toUpperCase();
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CouponCode)) return false;
        CouponCode that = (CouponCode) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
