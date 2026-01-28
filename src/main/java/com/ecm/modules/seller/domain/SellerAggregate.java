package com.ecm.modules.seller.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class SellerAggregate {

    private final String id;
    private final Long userId; // jis user ne seller account banaya

    private String storeName;
    private String email;

    private SellerStatus status;
    private SellerVerificationStatus verificationStatus;
    private SellerRating rating;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private SellerAggregate(Long userId,
                            String storeName,
                            String email) {

        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.storeName = storeName;
        this.email = email;

        this.status = SellerStatus.ACTIVE;
        this.verificationStatus = SellerVerificationStatus.PENDING;
        this.rating = new SellerRating(0.0);

        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // ✅ Factory method
    public static SellerAggregate create(Long userId,
                                         String storeName,
                                         String email) {
        return new SellerAggregate(userId, storeName, email);
    }

    // 🔒 Business behaviors

    public void verify() {
        this.verificationStatus = SellerVerificationStatus.VERIFIED;
        this.updatedAt = LocalDateTime.now();
    }

    public void rejectVerification() {
        this.verificationStatus = SellerVerificationStatus.REJECTED;
        this.status = SellerStatus.SUSPENDED;
        this.updatedAt = LocalDateTime.now();
    }

    public void block() {
        this.status = SellerStatus.BLOCKED;
        this.updatedAt = LocalDateTime.now();
    }

    public void unblock() {
        if (this.verificationStatus != SellerVerificationStatus.VERIFIED) {
            throw new IllegalStateException("Seller must be verified before activation");
        }
        this.status = SellerStatus.ACTIVE;
        this.updatedAt = LocalDateTime.now();
    }

    // read-only getters (few)
    public SellerStatus getStatus() {
        return status;
    }

    public SellerVerificationStatus getVerificationStatus() {
        return verificationStatus;
    }
    
}

