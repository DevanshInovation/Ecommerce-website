package com.ecm.modules.order.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderAggregate {

    private Long id;
    private Long userId;
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status;
    private LocalDateTime createdAt;

    protected OrderAggregate() {
        // JPA / framework ke liye
    }

    public OrderAggregate(Long userId, List<OrderItem> items) {
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Order must contain at least one item");
        }

        this.userId = userId;
        this.items = items;
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }

    public void markPaid() {
        if (this.status != OrderStatus.CREATED) {
            throw new IllegalStateException("Only created order can be paid");
        }
        this.status = OrderStatus.PAID;
    }

    public void cancel() {
        if (this.status == OrderStatus.SHIPPED) {
            throw new IllegalStateException("Shipped order cannot be cancelled");
        }
        this.status = OrderStatus.CANCELLED;
    }

    // getters only (NO setters)
}
