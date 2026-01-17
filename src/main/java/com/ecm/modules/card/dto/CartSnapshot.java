package com.ecm.modules.card.dto;

import java.util.List;

public record CartSnapshot(
        Long cartId,
        Long userId,
        List<CartItemSnapshot> items
) {}
