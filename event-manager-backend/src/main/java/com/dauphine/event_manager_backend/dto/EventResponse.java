package com.dauphine.event_manager_backend.dto;

import com.dauphine.event_manager_backend.model.Event;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventResponse {
    private UUID id;
    private String title;
    private String city;
    private String address;
    private LocalDateTime date;
    private String description;
    private LocalDateTime last_updated;
    private UUID categoryId;
    private UserResponse ownerResponse;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.city = event.getCity();
        this.address = event.getAddress();
        this.date = event.getDate();
        this.description = event.getDescription();
        this.last_updated = event.getLastUpdated();
        this.categoryId = event.getCategory().getId();
        this.ownerResponse = new UserResponse(event.getOwner());
    }

    public static List<EventResponse> ListEventResponse(List<Event> events) {
        List<EventResponse> eventResponses = new ArrayList<>();
        for (Event e: events) {
            eventResponses.add(new EventResponse(e));
        }
        return eventResponses;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(LocalDateTime last_updated) {
        this.last_updated = last_updated;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }

    public UserResponse getOwnerResponse() {
        return ownerResponse;
    }

    public void setOwnerResponse(UserResponse ownerResponse) {
        this.ownerResponse = ownerResponse;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
