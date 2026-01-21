package com.ecm.modules.payment.doamin;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class PaymentAggregate {

    private Long id;
    private Long orderId;
    private Money amount;
    private PaymentMethod method;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    protected PaymentAggregate() {
        // for framework
    }

    public PaymentAggregate(Long orderId, Money amount, PaymentMethod method) {
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.status = PaymentStatus.PENDING;
        this.createdAt = LocalDateTime.now();
    }

    public void markSuccess() {
        if (this.status != PaymentStatus.PENDING) {
            throw new IllegalStateException("Payment already processed");
        }
        this.status = PaymentStatus.SUCCESS;
    }

    public void markFailed() {
        if (this.status != PaymentStatus.PENDING) {
            throw new IllegalStateException("Payment already processed");
        }
        this.status = PaymentStatus.FAILED;
    }

    // getters only
}

