package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.dto.ReviewRequest;
import com.dauphine.event_manager_backend.dto.ReviewResponse;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Review;
import com.dauphine.event_manager_backend.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dauphine.event_manager_backend.dto.ReviewResponse.ListReviewResponse;

@RestController
@Tag(
        name = "Review API",
        description = "Endpoints for reviews"
)
@RequestMapping("/v1/reviews")
public class ReviewController {
    private final EventService eventService;

    public ReviewController(EventService eventService) {
        this.eventService = eventService;
    }

    @Operation(summary = "Get all reviews for event")
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponse>> getAllReviews(@PathVariable UUID id){
        List<Review> reviews = eventService.getAllReviews(id);
        return ResponseEntity.ok(ListReviewResponse(reviews));
    }

    @PostMapping("/{eventId}/reviews")
    @Operation(
            summary = "Create review for event",
            description = "Create a new review for a past event for a user."
    )
    public ResponseEntity<ReviewResponse> createReview(@PathVariable UUID eventId, @RequestBody ReviewRequest reviewRequest) throws EventNotFoundByIdException, UserNotFoundByIdException {
        Review review = eventService.createReview(eventId, reviewRequest.getUserId(), reviewRequest.getComment(), reviewRequest.getGrade());
        return new ResponseEntity<>(new ReviewResponse(review), HttpStatus.OK);
    }
}
