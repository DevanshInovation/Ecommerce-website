package com.ecm.modules.card.api;

public record AddToCartRequest(
        Long productId,
        int qty
) {}
