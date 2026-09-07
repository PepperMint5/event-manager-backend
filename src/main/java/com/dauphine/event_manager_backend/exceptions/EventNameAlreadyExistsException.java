package com.dauphine.event_manager_backend.exceptions;


public class EventNameAlreadyExistsException extends Exception {

    public EventNameAlreadyExistsException(String title) {
        super("Event with title " + title + " already exists.");
    }

}
