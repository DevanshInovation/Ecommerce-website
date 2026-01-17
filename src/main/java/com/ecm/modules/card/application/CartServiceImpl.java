package com.ecm.modules.card.application;

import com.ecm.modules.card.domain.CartAggregate;
import com.ecm.modules.card.dto.CartItemSnapshot;
import com.ecm.modules.card.dto.CartSnapshot;
import com.ecm.modules.card.infrastructure.persistence.CartJpaRepository;
import com.ecm.modules.user.application.UserService;
import com.ecm.modules.user.domain.UserSnapshot;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartJpaRepository cartRepository;
    private final UserService userService; // ✅ only dependency

    @Override
    public CartSnapshot getMyCart(String jwt) {
        UserSnapshot user = userService.findByJwt(jwt);

        CartAggregate cart = cartRepository
                .findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(new CartAggregate(user.getId())));

        return toSnapshot(cart);
    }

    @Override
    public void addToCart(String jwt, Long productId, int qty) {
        UserSnapshot user = userService.findByJwt(jwt);

        CartAggregate cart = cartRepository
                .findByUserId(user.getId())
                .orElseGet(() -> cartRepository.save(new CartAggregate(user.getId())));

        cart.addItem(productId, qty);
        cartRepository.save(cart);
    }

    // 🔥 MOST IMPORTANT METHOD
    private CartSnapshot toSnapshot(CartAggregate cart) {
        return new CartSnapshot(
                cart.getId(),
                cart.getUserId(),
                cart.getItems()
                        .stream()
                        .map(i -> new CartItemSnapshot(
                                i.getProductId(),
                                i.getQuantity()
                        ))
                        .collect(Collectors.toList())
        );
    }
}
