package com.dauphine.event_manager_backend.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import com.dauphine.event_manager_backend.model.Event;

import java.sql.Date;
import java.util.UUID;

@Entity
@Table(name = "review")
public class Review {
    @Id
    @Column(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "comment")
    private String comment;

    @Column(name = "grade")
    private int grade;

    @Column(name = "date_review")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date_review;

    public Review(Event event, User user, String comment, int grade) {
        this.id = UUID.randomUUID();
        this.event = event;
        this.user = user;
        this.comment = comment;
        this.grade = grade;
        this.date_review = new Date(System.currentTimeMillis());
    }

    public Review() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getDate_review() {
        return date_review;
    }

    public void setDate_review(Date date_review) {
        this.date_review = date_review;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", event=" + event +
                ", user=" + user +
                ", comment='" + comment + '\'' +
                ", grade=" + grade +
                ", date_review=" + date_review +
                '}';
    }
}