package com.ecm.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.legacy.model.Order;
import com.ecm.legacy.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
