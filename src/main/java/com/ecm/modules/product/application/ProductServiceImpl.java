package com.ecm.modules.product.application;

import org.springframework.stereotype.Service;

import com.ecm.modules.product.domain.ProductAggregate;
import com.ecm.modules.product.domain.ProductPrice;
import com.ecm.modules.product.dto.ProductSnapshot;
import com.ecm.modules.product.infrastructure.persistence.ProductJpaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductJpaRepository productRepository;

    @Override
    public ProductSnapshot createProduct(String name, String desc, double price) {

        ProductPrice productPrice = new ProductPrice(price);

        ProductAggregate product =
                new ProductAggregate(name, desc, productPrice);

        productRepository.save(product);

        return ProductSnapshot.from(product);
    }

    @Override
    public ProductSnapshot getProduct(Long productId) {

        ProductAggregate product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        return ProductSnapshot.from(product);
    }
}
