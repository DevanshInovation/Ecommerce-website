package com.ecm.modules.product.infrastructure.persistence;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ecm.modules.product.domain.ProductAggregate;
import com.ecm.modules.product.domain.ProductPrice;

@DataJpaTest
class ProductJpaRepositoryTest {

    @Autowired
    private ProductJpaRepository productRepository;

    @Test
    void shouldSaveAndFindProduct() {
        ProductAggregate product =
                new ProductAggregate("MacBook", "Apple laptop", new ProductPrice(150000));

        ProductAggregate saved = productRepository.save(product);

        Optional<ProductAggregate> found =
                productRepository.findById(saved.getId());

        assertTrue(found.isPresent());
        assertEquals("MacBook", found.get().getName());
    }
}

