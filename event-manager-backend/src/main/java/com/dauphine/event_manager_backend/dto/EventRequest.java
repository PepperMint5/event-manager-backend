package com.dauphine.event_manager_backend.dto;


import com.dauphine.event_manager_backend.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;

public class EventRequest {
    private String title;
    private String city;
    private String address;
    //@Temporal(TemporalType.DATE)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "UTC")
    private Date date;
    private LocalTime time;
    private String description;
    private UUID category;
    private UserResponse owner;

    public String getTitle() {
        return title;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Date getDate() {
        return date;
    }

    public LocalTime getTime() { return time; }

    public String getDescription() {
        return description;
    }

    public UUID getCategory() {
        return category;
    }

    public UserResponse getOwner() {
        return owner;
    }
}
