package com.ecm.modules.order.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_orders")
public class OrderAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private double totalAmount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    protected OrderAggregate() {} // JPA

    // ✅ DOMAIN FACTORY METHOD
    public static OrderAggregate create(Long userId) {
        OrderAggregate order = new OrderAggregate();
        order.userId = userId;
        order.status = OrderStatus.CREATED;
        order.totalAmount = 0.0;
        return order;
    }

    // ✅ REAL BUSINESS METHOD
    public void addItem(Long productId, int qty, double price) {
        items.add(new OrderItem(productId, qty, price));
        this.totalAmount += qty * price;
    }

    public void markPlaced() {
        this.status = OrderStatus.PLACED;
    }

    // getters
    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public double getTotalAmount() { return totalAmount; }
}
