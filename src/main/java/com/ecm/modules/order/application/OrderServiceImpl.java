package com.ecm.modules.order.application;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.ecm.modules.order.domain.OrderAggregate;
import com.ecm.modules.shared.events.OrderPlacedEvent;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderJpaRepository orderRepository;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Long placeOrder(Long userId) {

        // 1️⃣ Domain create
        OrderAggregate order = OrderAggregate.create(userId);

        // (normally cart se items aayenge – abhi skip)
        order.markPlaced();

        // 2️⃣ Save
        orderRepository.save(order);

        // 3️⃣ EVENT ANNOUNCEMENT (REAL PURPOSE)
        eventPublisher.publishEvent(
            new OrderPlacedEvent(
                order.getId(),
                userId,
                order.getTotalAmount()
            )
        );

        return order.getId();
    }
}
