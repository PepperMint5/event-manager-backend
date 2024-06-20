package com.dauphine.event_manager_backend.dto;

import com.dauphine.event_manager_backend.model.User;

import java.util.UUID;

public class UserResponse {
    private UUID id;

    private String username;

    public UserResponse(UUID id, String username) {
        this.id = id;
        this.username = username;
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
