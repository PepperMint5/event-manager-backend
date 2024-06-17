package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id) throws UserNotFoundByIdException;

    User getUserByUsername(String username) throws UserNotFoundByNameException;

    User updateUsername(UUID id, String newUsername) throws UserNotFoundByIdException;

    User updatePassword(UUID id, String newPassword) throws UserNotFoundByIdException;

    void deleteUserById(UUID id) throws UserNotFoundByIdException;

    User createUser(User user);
}
