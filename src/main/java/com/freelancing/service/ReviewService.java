
package com.freelancing.service;

import com.freelancing.model.Review;
import com.freelancing.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public void createReview(Review review) {

        if (review.getContractId() == null) {
            throw new IllegalArgumentException("Please select a contract");
        }

        if (review.getRating() == null ||
                review.getRating() < 1 ||
                review.getRating() > 5) {

            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5"
            );
        }

        reviewRepository.save(review);
    }
}
g