package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.model.Review;
import com.dauphine.event_manager_backend.repository.ReviewRepository;
import com.dauphine.event_manager_backend.service.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;


    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> getUserReviews(UUID userId) {
        return reviewRepository.findByUserId(userId);
    }

    @Override
    public void deleteReview(UUID reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public Review createReview(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(UUID reviewId, Review reviewDetails) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        review.setComment(reviewDetails.getComment());
        review.setGrade(reviewDetails.getGrade());

        return reviewRepository.save(review);
    }
}
