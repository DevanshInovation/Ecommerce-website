package com.ecm.modules.product.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import com.ecm.modules.product.domain.ProductAggregate;
import com.ecm.modules.product.domain.ProductPrice;
import com.ecm.modules.product.dto.ProductSnapshot;
import com.ecm.modules.product.infrastructure.persistence.ProductJpaRepository;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductJpaRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void shouldCreateProductSuccessfully() {
        // given
        ProductAggregate savedProduct =
                new ProductAggregate("iPhone", "Apple phone", new ProductPrice(99999));

        when(productRepository.save(any()))
                .thenReturn(savedProduct);

        // when
        ProductSnapshot snapshot =
                productService.createProduct("iPhone", "Apple phone", 99999);

        // then
        assertNotNull(snapshot);
        assertEquals("iPhone", snapshot.name());
        assertEquals(99999, snapshot.price());
    }
}
