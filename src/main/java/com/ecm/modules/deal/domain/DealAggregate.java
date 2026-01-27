package com.ecm.modules.deal.domain;

import java.util.UUID;

public class DealAggregate {

    private final String id;
    private String name;
    private DealType type;
    private DealStatus status;
    private DealValidity validity;

    private DealAggregate(String name,
                          DealType type,
                          DealValidity validity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.validity = validity;
        this.status = DealStatus.DRAFT;
    }

    // Factory method (IMPORTANT)
    public static DealAggregate create(String name,
                                       DealType type,
                                       DealValidity validity) {
        return new DealAggregate(name, type, validity);
    }

    // Business behavior
    public void activate() {
        if (!validity.isActiveNow()) {
            throw new IllegalStateException("Deal validity not started or expired");
        }
        this.status = DealStatus.ACTIVE;
    }

    public void disable() {
        this.status = DealStatus.DISABLED;
    }

    // getters (sirf read)
    public String getId() {
        return id;
    }

    public DealStatus getStatus() {
        return status;
    }
}

