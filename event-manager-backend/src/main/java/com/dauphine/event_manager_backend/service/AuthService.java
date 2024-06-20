package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.model.User;

public interface AuthService {
    User login(String username, String password);

    boolean registerUser(String username, String plainPassword);
}
