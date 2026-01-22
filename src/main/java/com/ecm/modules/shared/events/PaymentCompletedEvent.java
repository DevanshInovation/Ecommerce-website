package com.ecm.modules.shared.events;

public record PaymentCompletedEvent(
        Long orderId,
        boolean success
) {}

