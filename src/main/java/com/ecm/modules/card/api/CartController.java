package com.ecm.modules.card.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.ecm.modules.card.application.CartService;
import com.ecm.modules.card.dto.CartSnapshot;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    // 1️⃣ Get my cart
    @GetMapping
    public CartSnapshot getMyCart(
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        String jwt = extractJwt(authorizationHeader);
        return cartService.getMyCart(jwt);
    }

    // 2️⃣ Add item to cart
    @PostMapping("/items")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addToCart(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody AddToCartRequest request
    ) {
        String jwt = extractJwt(authorizationHeader);
        cartService.addToCart(jwt, request.productId(), request.qty());
    }

    // 🔒 helper method (controller level)
    private String extractJwt(String header) {
        // "Bearer eyJhbGciOiJIUzI1NiJ9..."
        return header.replace("Bearer ", "");
    }
}
