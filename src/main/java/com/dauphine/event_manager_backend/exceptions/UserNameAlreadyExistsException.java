package com.dauphine.event_manager_backend.exceptions;


public class UserNameAlreadyExistsException extends Exception {

    public UserNameAlreadyExistsException(String username) {
        super("Username " + username + " is already taken.");
    }

}
