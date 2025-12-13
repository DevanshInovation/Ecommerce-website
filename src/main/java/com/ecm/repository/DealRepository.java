package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Deal;

public interface DealRepository extends JpaRepository<Deal, Long>{

}
