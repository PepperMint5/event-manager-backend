package com.dauphine.event_manager_backend.service;
import com.dauphine.event_manager_backend.exceptions.UserNameAlreadyExistsException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.User;

public interface AuthService {
    User login(String username, String password) throws UserNotFoundByNameException;;
    //TODO le register doit renvoyer le userId ou le user entier de l'user qui a été créé pour l'envoyer en base
    User registerUser(String username, String plainPassword) throws UserNameAlreadyExistsException;

}
