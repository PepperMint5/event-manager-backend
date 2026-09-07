package com.dauphine.event_manager_backend.dto;


import java.sql.Date;
import java.time.LocalTime;
import java.util.UUID;

public class EventRequest {
    private String title;
    private String city;
    private String address;
    private Date date;
    private LocalTime time;
    private String description;
    private UUID category;
    private UUID owner;

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

    public LocalTime getTime() {
        return time;
    }

    public String getDescription() {
        return description;
    }

    public UUID getCategory() {
        return category;
    }

    public UUID getOwner() {
        return owner;
    }
}
