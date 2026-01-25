package com.ecm.modules.coupon.Application;

import com.ecm.modules.coupon.dto.CouponSnapshot;

public interface CouponService {

    CouponSnapshot createCoupon(
            String code,
            String type,
            double value
    );
}
