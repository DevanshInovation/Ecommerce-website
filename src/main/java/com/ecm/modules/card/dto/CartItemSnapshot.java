package com.ecm.modules.card.dto;

public record CartItemSnapshot(
        Long productId,
        int quantity
) {}
