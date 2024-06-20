package com.dauphine.event_manager_backend.dto;

import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Participation;

public class ParticipationResponse {
    private UserResponse userResponse;
    private Event event;

    public ParticipationResponse(Participation participation) {
        this.userResponse = new UserResponse(participation.getUser());
        this.event = participation.getEvent();
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public Event getEvent() {
        return event;
    }
}
