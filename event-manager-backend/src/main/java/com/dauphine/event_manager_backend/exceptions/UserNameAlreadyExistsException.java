package com.dauphine.event_manager_backend.exceptions;


public class UserNameAlreadyExistsException extends Exception {

    public UserNameAlreadyExistsException(String username) {
        super("User with username " + username + " already exists.");
    }

}
