package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.UserNameAlreadyExistsException;
import com.dauphine.event_manager_backend.model.User;

public interface AuthService {
    /**
     *
     * @param username
     * @param password
     * @return
     */
    User login(String username, String password);

    /**
     *
     * @param username
     * @param plainPassword
     * @return
     * @throws UserNameAlreadyExistsException
     */
    User registerUser(String username, String plainPassword) throws UserNameAlreadyExistsException;

}
