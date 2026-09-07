package com.dauphine.event_manager_backend.dto;


import java.util.UUID;

public class ReviewRequest {
    private UUID eventId;
    private UUID userId;
    private String comment;
    private int grade;

    public UUID getEventId() {
        return eventId;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
