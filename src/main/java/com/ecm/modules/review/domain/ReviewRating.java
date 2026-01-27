package com.ecm.modules.review.domain;

public class ReviewRating {

    private final int value;

    public ReviewRating(int value) {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }
        this.value = value;
    }

    public int value() {
        return value;
    }
}

