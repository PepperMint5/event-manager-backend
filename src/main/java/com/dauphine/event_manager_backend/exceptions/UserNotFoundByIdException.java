package com.dauphine.event_manager_backend.exceptions;

import java.util.UUID;

public class UserNotFoundByIdException extends Exception {

    public UserNotFoundByIdException(UUID id) {
        super("User with id " + id + " not found");
    }
}
