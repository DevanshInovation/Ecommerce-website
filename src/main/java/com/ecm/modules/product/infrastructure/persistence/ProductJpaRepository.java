package com.ecm.modules.product.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ecm.modules.product.domain.ProductAggregate;

public interface ProductJpaRepository
        extends JpaRepository<ProductAggregate, Long> {
}
