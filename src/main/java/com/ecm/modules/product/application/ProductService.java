package com.ecm.modules.product.application;

import com.ecm.modules.product.dto.ProductSnapshot;

public interface ProductService {

    ProductSnapshot createProduct(String name, String desc, double price);

    ProductSnapshot getProduct(Long productId);
}

