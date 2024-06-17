package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.UserNameAlreadyExistsException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;

public interface AuthService {
    boolean login(String username, String password) throws UserNotFoundByNameException;

    boolean registerUser(String username, String plainPassword) throws UserNameAlreadyExistsException;

}
