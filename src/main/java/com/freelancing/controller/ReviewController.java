
package com.freelancing.controller;

import com.freelancing.model.Review;
import com.freelancing.service.ReviewService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public String showReviews(Model model) {
        model.addAttribute("reviews", reviewService.getAllReviews());
        return "reviews";
    }

    @PostMapping("/reviews/create")
    public String createReview(@ModelAttribute Review review) {
        reviewService.createReview(review);
        return "redirect:/reviews";
    }
}
