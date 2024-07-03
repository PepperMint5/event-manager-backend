package com.dauphine.event_manager_backend.exceptions;

import com.dauphine.event_manager_backend.model.User;

public class FriendshipAlreadyExistException extends Exception {
    public FriendshipAlreadyExistException(User user) {
        super("Yout are already friend with " + user.getUsername() + ".");
    }
}
