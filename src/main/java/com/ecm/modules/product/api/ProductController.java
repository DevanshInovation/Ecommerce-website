package com.ecm.modules.product.api;

import org.springframework.web.bind.annotation.*;

import com.ecm.modules.product.application.ProductService;
import com.ecm.modules.product.dto.ProductSnapshot;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ProductSnapshot createProduct(
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam double price
    ) {
        return productService.createProduct(name, description, price);
    }

    @GetMapping("/{id}")
    public ProductSnapshot getProduct(@PathVariable Long id) {
        return productService.getProduct(id);
    }
}
