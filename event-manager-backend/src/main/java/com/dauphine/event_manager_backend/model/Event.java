package com.dauphine.event_manager_backend.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "event")
public class Event {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "title")
    private String title;

    @Column(name = "city")
    private String city;

    @Column(name = "address")
    private String address;

    @Column(name = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime date;

    @Column(name = "description")
    private String description;

    @Column(name = "last_updated")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime last_updated;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
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

    public LocalDateTime getLastUpdated() {
        return last_updated;
    }

    public void setLastUpdated(LocalDateTime last_updated) {
        this.last_updated = last_updated;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Event(String title, String city, String address, LocalDateTime date, String description, LocalDateTime last_updated, Category category, User owner) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.city = city;
        this.address = address;
        this.date = date;
        this.description = description;
        this.last_updated = LocalDateTime.now();
        this.category = category;
        this.owner = owner;
    }

    public Event(){}

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", city='" + city + '\'' +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", owner=" + owner +
                '}';
    }
}
