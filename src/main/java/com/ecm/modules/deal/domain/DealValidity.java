package com.ecm.modules.deal.domain;

import java.time.LocalDateTime;

public class DealValidity {

    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public DealValidity(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public boolean isActiveNow() {
        LocalDateTime now = LocalDateTime.now();
        return now.isAfter(startAt) && now.isBefore(endAt);
    }

    public LocalDateTime startAt() {
        return startAt;
    }

    public LocalDateTime endAt() {
        return endAt;
    }
}

