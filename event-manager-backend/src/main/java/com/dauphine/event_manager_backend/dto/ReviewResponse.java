package com.dauphine.event_manager_backend.dto;


import com.dauphine.event_manager_backend.model.Review;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReviewResponse {
    private UUID id;
    private EventResponse eventId;
    private UserResponse userId;
    private String comment;
    private int grade;
    private Date date_review;

    public ReviewResponse() {}

    public ReviewResponse(Review review) {
        this.id = review.getId();
        this.eventId = new EventResponse(review.getEvent());
        this.userId = new UserResponse(review.getUser());
        this.comment = review.getComment();
        this.grade = review.getGrade();
        this.date_review = review.getDate_review();
    }

    public ReviewResponse(UUID id, EventResponse eventId, UserResponse userId, String comment, int grade, Date date_review) {
        this.id = id;
        this.eventId = eventId;
        this.userId = userId;
        this.comment = comment;
        this.grade = grade;
        this.date_review = date_review;
    }

    public static List<ReviewResponse> ListReviewResponse(List<Review> reviews) {
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review r: reviews) {
            reviewResponses.add(new ReviewResponse(r));
        }
        return reviewResponses;
    }

    public EventResponse getEventId() {
        return eventId;
    }

    public UserResponse getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public int getGrade() {
        return grade;
    }

    public UUID getId() {
        return id;
    }

    public Date getDate_review() {
        return date_review;
    }
}
