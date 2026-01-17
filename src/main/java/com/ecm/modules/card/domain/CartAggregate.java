package com.ecm.modules.card.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "cart")
public class CartAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> items = new ArrayList<>();

    protected CartAggregate() {}

    public CartAggregate(Long userId) {
        this.userId = userId;
    }

    public void addItem(Long productId, int qty) {
        CartItem item = items.stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (item == null) {
            items.add(new CartItem(productId, qty));
        } else {
            item.increaseQty(qty);
        }
    }
}
