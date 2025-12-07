package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByCategoryId(String categoryId);
}
