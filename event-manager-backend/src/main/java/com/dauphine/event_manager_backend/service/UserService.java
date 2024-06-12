package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.model.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID id);

    User getUserByUsername(String username);

    User updateUsername(UUID id, String newUsername);

    User updatePassword(UUID id, String newPassword);

    void deleteUserById(UUID id);

    User createUser(User user);
}
