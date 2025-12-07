package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
