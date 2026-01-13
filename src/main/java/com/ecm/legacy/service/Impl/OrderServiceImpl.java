package com.ecm.legacy.service.Impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecm.legacy.domain.OrderStatus;
import com.ecm.legacy.domain.PaymentStatus;
import com.ecm.legacy.model.Address;
import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.CartItem;
import com.ecm.legacy.model.Order;
import com.ecm.legacy.model.OrderItem;
import com.ecm.legacy.model.User;
import com.ecm.legacy.repository.AddressRepository;
import com.ecm.legacy.repository.OrderItemRepository;
import com.ecm.legacy.repository.OrderRepository;
import com.ecm.legacy.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

	private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;
	private final OrderItemRepository orderItemRepository;
	
	@Override
	public Set<Order> createOrder(User user, Address shippingAddress, Cart cart) {

	    // user address list me add + save
	    if (!user.getAddress().contains(shippingAddress)) {
	        user.getAddress().add(shippingAddress);
	    }
	    Address address = addressRepository.save(shippingAddress);

	    // sellerId -> us seller ke cart items
	    Map<Long, List<CartItem>> itemsBySeller =
	            cart.getCartItems()
	                .stream()
	                .collect(Collectors.groupingBy(
	                        item -> item.getProduct().getSeller().getId()
	                ));

	    Set<Order> orders = new HashSet<Order>();

	    for (Map.Entry<Long, List<CartItem>> entry : itemsBySeller.entrySet()) {

	        Long sellerId = entry.getKey();
	        List<CartItem> items = entry.getValue();

	        int totalOrderPrice =
	                items.stream()
	                     .mapToInt(CartItem::getSellingPrice)
	                     .sum();

	        int totalMrpPrice =
	                items.stream()
	                     .mapToInt(CartItem::getMrpPrice)
	                     .sum();

	        int totalItem =
	                items.stream()
	                     .mapToInt(CartItem::getQuantity)
	                     .sum();

	        // --------- create Order ----------
	        Order createdOrder = new Order();
	        createdOrder.setUserId(user);
	        createdOrder.setSellerId(sellerId);
	        createdOrder.setTotalMrpPrice(totalMrpPrice);
	        createdOrder.setTotalSellingPrice(totalOrderPrice);
	        createdOrder.setTotalItem(totalItem);
	        createdOrder.setShippingAddress(address);
	        createdOrder.setOrderStatus(OrderStatus.PENDING);
	        createdOrder.getPaymentsDetails().setStatus(PaymentStatus.PENDING);

	        Order savedOrder = orderRepository.save(createdOrder);
	        orders.add(savedOrder);

	        // --------- create OrderItems ----------
	        List<OrderItem> orderItems = new ArrayList<>();

	        for (CartItem item : items) {
	            OrderItem orderItem = new OrderItem();
	            orderItem.setOrder(savedOrder);
	            orderItem.setMrpPrice(item.getMrpPrice());
	            orderItem.setProduct(item.getProduct());
	            orderItem.setQuantity(item.getQuantity());
	            orderItem.setSize(item.getSize());
	            orderItem.setUserId(item.getUserId());
	            orderItem.setSellingPrice(item.getSellingPrice());

	            savedOrder.getOrderItem().add(orderItem);

	            OrderItem savedOrderItem = orderItemRepository.save(orderItem);
	            orderItems.add(savedOrderItem);
	        }
	    }

	    return orders;
	}


	@Override
	public Order findOrderById(Long Id) throws Exception {
		
		return orderRepository.findById(Id).orElseThrow(()->
		new Exception("orde not found..")
				);
	}

	@Override
	public List<Order> userOrderHistory(Long userId) {
		return orderRepository.findByUserId_Id(userId);
	}

	@Override
	public List<Order> sellersOrder(Long sellerId) {
		return orderRepository.findBySellerId(sellerId);
	}

	@Override
	public Order updateOrderStatus(Long orderId, OrderStatus orderStatus ) throws Exception {
		Order order=findOrderById(orderId);
		order.setOrderStatus(orderStatus);
		return orderRepository.save(order);
	}

	@Override
	public Order cancelOrder(Long orderId, User user) throws Exception {
		Order order=findOrderById(orderId);
		  if (!user.getId().equals(order.getUserId().getId())) {
		        throw new Exception("you don't have access to this order");
		    }
		  
		order.setOrderStatus(OrderStatus.CANCELLED);
		return orderRepository.save(order);
	}


	@Override
	public OrderItem findOrderItemById(Long Id) throws Exception {
		// TODO Auto-generated method stub
		return orderItemRepository.findById(Id).orElseThrow(()->
		new Exception("order item not exist...")
				);
	}
}
