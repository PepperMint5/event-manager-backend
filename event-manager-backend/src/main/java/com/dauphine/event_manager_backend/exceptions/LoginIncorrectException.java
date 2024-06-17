package com.dauphine.event_manager_backend.exceptions;

public class LoginIncorrectException extends Exception {

    public LoginIncorrectException(String username) {
        super("Loging incorrect for user : " + username);
    }


}
