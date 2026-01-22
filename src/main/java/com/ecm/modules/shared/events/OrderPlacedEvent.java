package com.ecm.modules.shared.events;

public record OrderPlacedEvent(
        Long orderId,
        Long userId,
        double amount
) {}
