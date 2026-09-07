package com.dauphine.event_manager_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "participation")
public class Participation {

    @EmbeddedId
    private ParticipationId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @MapsId("eventId")
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

    public Participation() {
    }

    public Participation(User user, Event event) {
        this.id = new ParticipationId(user.getId(), event.getId());
        this.user = user;
        this.event = event;
    }

    public ParticipationId getId() {
        return id;
    }

    public void setId(ParticipationId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

}
