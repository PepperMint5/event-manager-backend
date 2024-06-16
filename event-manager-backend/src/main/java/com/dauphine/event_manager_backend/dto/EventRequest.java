package com.dauphine.event_manager_backend.dto;

import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.model.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class EventRequest {
    private String title;
    private String city;
    private String address;
    private LocalDateTime date;
    private String description;
    private UUID categoryId;
    private UUID userId;

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public UUID getUserId() {
        return userId;
    }
}
