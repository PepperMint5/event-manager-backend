package com.dauphine.event_manager_backend.exceptions;

import java.util.UUID;

public class EventNotFoundByIdException extends Exception {

    public EventNotFoundByIdException(UUID id) {
        super("Event with id " + id + " not found");
    }
}
