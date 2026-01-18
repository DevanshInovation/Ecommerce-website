package com.ecm.modules.product.dto;

import com.ecm.modules.product.domain.ProductAggregate;

public record ProductSnapshot(
        Long id,
        String name,
        String description,
        double price,
        boolean active
) {
    public static ProductSnapshot from(ProductAggregate product) {
        return new ProductSnapshot(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice().amount(),
                product.isActive()
        );
    }
}
