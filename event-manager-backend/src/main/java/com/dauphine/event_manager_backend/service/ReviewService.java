package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.model.Review;

import java.util.List;
import java.util.UUID;

public interface ReviewService {


    /**
     *
     * @param userId
     * @return
     */
    List<Review> getUserReviews(UUID userId);

    /**
     *
     * @param reviewId
     */
    void deleteReview(UUID reviewId);

    /**
     *
     * @param review
     * @return
     */
    Review createReview(Review review);

    /**
     *
     * @param reviewId
     * @param reviewDetails
     * @return
     */
    Review updateReview(UUID reviewId, Review reviewDetails);
}
