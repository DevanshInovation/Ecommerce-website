package com.ecm.modules.inventory.application;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.ecm.modules.shared.events.OrderPlacedEvent;
import com.ecm.modules.shared.events.InventoryReservedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class InventoryEventListener {

    @EventListener
    public void onOrderPlaced(OrderPlacedEvent event) {

        // 🔒 Inventory logic yahan aayega baad me
        System.out.println("Inventory reserved for order " + event.orderId());

        // Next event baad me publish karenge
    }
}
