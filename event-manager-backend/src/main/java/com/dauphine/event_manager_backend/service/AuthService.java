package com.dauphine.event_manager_backend.service;

public interface AuthService {
    boolean login(String username, String password);

    boolean registerUser(String username, String plainPassword);
}
