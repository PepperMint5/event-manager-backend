package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.model.Review;
import com.dauphine.event_manager_backend.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name = "Review API",
        description = "Endpoints for reviews"
)
@RequestMapping("/v1/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/user/{userId}")
    @Operation(
            summary = "Get reviews by user ID",
            description = "Return all reviews created by a specific user"
    )
    public ResponseEntity<List<Review>> getUserReviews(@PathVariable UUID userId) {
        List<Review> reviews = reviewService.getUserReviews(userId);
        return ResponseEntity.ok(reviews);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a review by ID",
            description = "Delete a review by its ID"
    )
    public ResponseEntity<Void> deleteReview(@PathVariable UUID id) {
        reviewService.deleteReview(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    @Operation(
            summary = "Create a new review",
            description = "Create a new review"
    )
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review createdReview = reviewService.createReview(review);
        return ResponseEntity.ok(createdReview);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a review by ID",
            description = "Update a review by its ID"
    )
    public ResponseEntity<Review> updateReview(@PathVariable UUID id, @RequestBody Review reviewDetails) {
        Review updatedReview = reviewService.updateReview(id, reviewDetails);
        return ResponseEntity.ok(updatedReview);
    }
}
