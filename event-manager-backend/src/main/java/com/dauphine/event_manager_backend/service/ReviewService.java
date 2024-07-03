package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.model.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {


    List<Review> getUserReviews(UUID userId);

    void deleteReview(UUID reviewId);

    Review createReview(Review review);

    Review updateReview(UUID reviewId, Review reviewDetails);
}
