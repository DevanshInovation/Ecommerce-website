package com.ecm.modules.coupon.domain;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class CouponAggregate {

    private Long id;
    private CouponCode code;
    private CouponDiscount discount;
    private LocalDate expiryDate;
    private CouponStatus status;

    private CouponAggregate() {}

    public static CouponAggregate create(
            String code,
            CouponType type,
            double discountValue,
            LocalDate expiryDate
    ) {
        CouponAggregate coupon = new CouponAggregate();
        coupon.code = new CouponCode(code);
        coupon.discount = new CouponDiscount(type, discountValue);
        coupon.expiryDate = expiryDate;
        coupon.status = CouponStatus.ACTIVE;
        return coupon;
    }

    public boolean isValid() {
        return status == CouponStatus.ACTIVE &&
               expiryDate.isAfter(LocalDate.now());
    }

    // getters (no setters — discipline)
}
