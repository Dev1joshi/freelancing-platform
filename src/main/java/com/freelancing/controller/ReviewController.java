
package com.freelancing.controller;

import com.freelancing.model.Review;
import com.freelancing.service.ReviewService;
import com.freelancing.service.ContractService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ReviewController {

    private final ReviewService reviewService;
    private final ContractService contractService;

    public ReviewController(
            ReviewService reviewService,
            ContractService contractService) {

        this.reviewService = reviewService;
        this.contractService = contractService;
    }

    @GetMapping("/reviews")
    public String showReviews(Model model) {

        model.addAttribute("reviews", reviewService.getAllReviews());
        model.addAttribute("review", new Review());
        model.addAttribute("contracts", contractService.getAllContracts());

        return "reviews";
    }

    @PostMapping("/reviews/create")
    public String createReview(@ModelAttribute Review review) {

        reviewService.createReview(review);

        return "redirect:/reviews";
    }
}
