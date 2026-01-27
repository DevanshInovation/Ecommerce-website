package com.ecm.modules.review.domain;
import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewAggregate {

    private final String id;
    private final Long userId;
    private final Long targetId; // productId / sellerId
    private final ReviewTargetType targetType;

    private ReviewRating rating;
    private String comment;
    private ReviewStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private ReviewAggregate(Long userId,
                            Long targetId,
                            ReviewTargetType targetType,
                            ReviewRating rating,
                            String comment) {

        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.targetId = targetId;
        this.targetType = targetType;
        this.rating = rating;
        this.comment = comment;
        this.status = ReviewStatus.ACTIVE;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Factory method
    public static ReviewAggregate create(Long userId,
                                         Long targetId,
                                         ReviewTargetType targetType,
                                         ReviewRating rating,
                                         String comment) {
        return new ReviewAggregate(userId, targetId, targetType, rating, comment);
    }

    // Business behavior
    public void edit(ReviewRating newRating, String newComment) {
        if (this.status == ReviewStatus.DELETED) {
            throw new IllegalStateException("Cannot edit deleted review");
        }
        this.rating = newRating;
        this.comment = newComment;
        this.status = ReviewStatus.EDITED;
        this.updatedAt = LocalDateTime.now();
    }

    public void delete() {
        this.status = ReviewStatus.DELETED;
        this.updatedAt = LocalDateTime.now();
    }

    // read-only getters
    public ReviewRating getRating() {
        return rating;
    }

    public ReviewStatus getStatus() {
        return status;
    }
}



