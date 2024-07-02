package com.dauphine.event_manager_backend.dto;

import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.model.Event;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EventResponse {
    private UUID id;
    private String title;
    private String city;
    private String address;
    private Date date;
    private LocalTime time;
    private String description;
    private Date last_updated;
    private Category category;
    private UserResponse owner;

    public EventResponse(Event event) {
        this.id = event.getId();
        this.title = event.getTitle();
        this.city = event.getCity();
        this.address = event.getAddress();
        this.date = event.getDate();
        this.time = event.getTime();
        this.description = event.getDescription();
        this.last_updated = event.getLastUpdated();
        this.category = event.getCategory();
        this.owner = new UserResponse(event.getOwner());
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLast_updated() {
        return last_updated;
    }

    public void setLast_updated(Date last_updated) {
        this.last_updated = last_updated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public UserResponse getOwner() {
        return owner;
    }

    public void setOwner(UserResponse owner) {
        this.owner = owner;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
