package com.ecm.modules.product.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "product_product")
public class ProductAggregate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @Embedded
    private ProductPrice price;

    private boolean active = true;

    protected ProductAggregate() {}

    // ✅ CORRECT CONSTRUCTOR
    public ProductAggregate(String name, String description, ProductPrice price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    // getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public ProductPrice getPrice() { return price; }
    public boolean isActive() { return active; }
}
