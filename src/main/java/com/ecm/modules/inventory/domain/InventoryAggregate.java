package com.ecm.modules.inventory.domain;

public class InventoryAggregate {

    private Long id;
    private Long productId;
    private StockQuantity quantity;
    private InventoryStatus status;

    protected InventoryAggregate() {
        // for JPA / framework
    }

    public InventoryAggregate(Long productId, int initialQuantity) {
        this.productId = productId;
        this.quantity = new StockQuantity(initialQuantity);
        this.status = InventoryStatus.AVAILABLE;
    }

    public void reduceStock(int qty) {
        this.quantity = this.quantity.decrease(qty);

        if (this.quantity.isEmpty()) {
            this.status = InventoryStatus.OUT_OF_STOCK;
        }
    }

    public void addStock(int qty) {
        this.quantity = this.quantity.increase(qty);
        this.status = InventoryStatus.AVAILABLE;
    }

    // getters only
}

