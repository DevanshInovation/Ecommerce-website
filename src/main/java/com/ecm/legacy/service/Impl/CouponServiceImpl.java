package com.ecm.legacy.service.Impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.Coupon;
import com.ecm.legacy.model.User;
import com.ecm.legacy.repository.CartRepository;
import com.ecm.legacy.repository.CouponRepository;
import com.ecm.legacy.repository.UserRepository;
import com.ecm.legacy.service.CouponService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService{
	
	private final CouponRepository couponRepository;
	private final CartRepository cartRepository;
	private final UserRepository userRepository;
	
	
	@Override
	public Cart applyCoupon(String code, double orderValue, User user) throws Exception {

	    Coupon coupon = couponRepository.findByCode(code);
	    Cart cart = cartRepository.findByUserId(user.getId());

	    if (coupon == null) {
	        throw new Exception("coupon not valid");
	    }

	    if (user.getUsedCoupon().contains(coupon)) {
	        throw new Exception("coupon already used");
	    }

	    if (orderValue < coupon.getMinimumOderValue()) {
	        throw new Exception("valid for minimum order value " + coupon.getMinimumOderValue());
	    }

	    if (coupon.isActive()
	            && LocalDate.now().isAfter(coupon.getValidityStartDate())
	            && LocalDate.now().isBefore(coupon.getValidityEndDate())) {

	        user.getUsedCoupon().add(coupon);
	        userRepository.save(user);

	        double discountedPrice =
	                (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100.0;

	        cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountedPrice);
	        cart.setCouponCode(code);
	        cartRepository.save(cart);

	        return cart;
	    }

	    throw new Exception("coupon not valid");
	}


	@Override
	public Cart removeCoupon(String code, User user) throws Exception {

	    Coupon coupon = couponRepository.findByCode(code);

	    if (coupon == null) {
	        throw new Exception("coupon not found...");
	    }

	    Cart cart = cartRepository.findByUserId(user.getId());

	    double discountedPrice =
	            (cart.getTotalSellingPrice() * coupon.getDiscountPercentage()) / 100.0;

	    cart.setTotalSellingPrice(cart.getTotalSellingPrice() - discountedPrice);
	    cart.setCouponCode(null);

	    return cart;
	}


	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public Coupon createCoupon(Coupon coupon) {
	    return couponRepository.save(coupon);
	}

	@Override
	public List<Coupon> findAllCoupons() {
	    return couponRepository.findAll();
	}

	@Override
	@PreAuthorize("hasRole('ADMIN')")
	public void deleteCoupon(Long id) throws Exception {
	    findCouponById(id);
	    couponRepository.deleteById(id);
	}

	@Override
	public Coupon findCouponById(Long id) throws Exception {
	    return couponRepository.findById(id)
	            .orElseThrow(() -> new Exception("coupon not found"));
	}


}
