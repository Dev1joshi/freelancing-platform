
package com.freelancing.model;

import java.time.LocalDateTime;

public class Review {

    private int reviewId;
    private int contractId;
    private int clientId;
    private int freelancerId;
    private int rating;
    private String reviewComment;
    private LocalDateTime createdAt;

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getFreelancerId() {
        return freelancerId;
    }

    public void setFreelancerId(int freelancerId) {
        this.freelancerId = freelancerId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    public void setReviewComment(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
