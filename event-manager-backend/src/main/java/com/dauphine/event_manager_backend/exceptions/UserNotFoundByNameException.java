package com.dauphine.event_manager_backend.exceptions;


public class UserNotFoundByNameException extends Exception  {

    public UserNotFoundByNameException(String name) {
        super("User with name " + name + " not found");
    }
}
