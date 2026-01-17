package com.ecm.modules.card.infrastructure.persistence;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.modules.card.domain.CartAggregate;


public interface CartJpaRepository extends JpaRepository<CartAggregate, Long> {

    Optional<CartAggregate> findByUserId(Long userId);
}