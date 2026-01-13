package com.ecm.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.ecm.legacy.domain.OrderStatus;
import com.ecm.legacy.domain.PaymentStatus;
import com.ecm.legacy.model.Address;
import com.ecm.legacy.model.Cart;
import com.ecm.legacy.model.CartItem;
import com.ecm.legacy.model.Order;
import com.ecm.legacy.model.OrderItem;
import com.ecm.legacy.model.PaymentDetails;
import com.ecm.legacy.model.Product;
import com.ecm.legacy.model.Seller;
import com.ecm.legacy.model.User;
import com.ecm.legacy.repository.AddressRepository;
import com.ecm.legacy.repository.OrderItemRepository;
import com.ecm.legacy.repository.OrderRepository;
import com.ecm.legacy.service.Impl.OrderServiceImpl;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createOrder_shouldCreateOrdersPerSellerAndSaveItems() {
        // ARRANGE
        User user = new User();
        user.setId(1L);
        user.setAddress(new HashSet<>()); // Set<Address>

        Address shipping = new Address();
        shipping.setId(10L);

        Cart cart = new Cart();
        Set<CartItem> cartItems = new HashSet<>();

        // 1st cart item (seller 100)
        CartItem item1 = new CartItem();
        item1.setMrpPrice(100);
        item1.setSellingPrice(80);
        item1.setQuantity(2);

        Product p1 = new Product();
        Seller s1 = new Seller();
        s1.setId(100L);
        p1.setSeller(s1);
        item1.setProduct(p1);
        item1.setUserId(user.getId());
        cartItems.add(item1);

        // 2nd cart item (same seller 100)
        CartItem item2 = new CartItem();
        item2.setMrpPrice(200);
        item2.setSellingPrice(150);
        item2.setQuantity(1);

        Product p2 = new Product();
        p2.setSeller(s1); // same seller
        item2.setProduct(p2);
        item2.setUserId(user.getId());
        cartItems.add(item2);

        cart.setCartItems(cartItems);

        when(addressRepository.save(shipping)).thenReturn(shipping);

        // yahan createdOrder (jo service ke andar banega) ko hi wapas return karna hai
        when(orderRepository.save(any(Order.class))).thenAnswer(invocation -> {
            Order o = invocation.getArgument(0, Order.class);
            // ensure paymentsDetails null na ho
            if (o.getPaymentsDetails() == null) {
                o.setPaymentsDetails(new PaymentDetails());
                o.getPaymentsDetails().setStatus(PaymentStatus.PENDING);
            }
            return o;
        });

        when(orderItemRepository.save(any(OrderItem.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // ACT
        Set<Order> result = orderService.createOrder(user, shipping, cart);

        // ASSERT
        assertEquals(1, result.size()); // 1 seller => 1 order
        Order order = result.iterator().next();
        assertEquals(OrderStatus.PENDING, order.getOrderStatus());
        assertEquals(PaymentStatus.PENDING, order.getPaymentsDetails().getStatus());

        verify(addressRepository).save(shipping);
        verify(orderRepository, times(1)).save(any(Order.class));
        verify(orderItemRepository, times(2)).save(any(OrderItem.class));
    }
}
