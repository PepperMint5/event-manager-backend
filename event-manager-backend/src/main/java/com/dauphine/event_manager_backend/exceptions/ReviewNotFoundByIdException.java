package com.dauphine.event_manager_backend.exceptions;

import java.util.UUID;

public class ReviewNotFoundByIdException extends Exception  {

    public ReviewNotFoundByIdException(UUID id) {
        super("Review with id " + id + " not found");
    }
}
