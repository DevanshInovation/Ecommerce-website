package com.ecm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecm.model.Order;
import com.ecm.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
