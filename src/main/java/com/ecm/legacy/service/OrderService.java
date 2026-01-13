package com.ecm.legacy.service;

import java.util.List;
import java.util.Set;

import com.ecm.legacy.domain.OrderStatus;
import com.ecm.legacy.model.Address;
import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.Order;
import com.ecm.legacy.model.OrderItem;
import com.ecm.legacy.model.User;

public interface OrderService {
	Set<Order> createOrder(User user, Address shippingAddress, Cart cart);
	Order findOrderById(Long Id) throws Exception;
	List<Order> userOrderHistory(Long userId);
	List<Order> sellersOrder(Long sellerId);
	Order updateOrderStatus(Long orderId, OrderStatus orderStatus) throws Exception;
	Order cancelOrder(Long orderId, User user) throws Exception;
	OrderItem findOrderItemById(Long Id) throws Exception;
}
