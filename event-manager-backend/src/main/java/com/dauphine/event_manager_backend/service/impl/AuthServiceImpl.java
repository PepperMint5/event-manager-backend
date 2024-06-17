package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.exceptions.*;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.repository.UserRepository;
import com.dauphine.event_manager_backend.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public boolean login(String username, String password) throws UserNotFoundByNameException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundByNameException(username));
        if (passwordEncoder.matches(password, user.getPassword())) {
            return true; // Connexion succeed
        }
        else {  // password incorrect
            return false;
        }
    }

    @Override
    public boolean registerUser(String username, String plainPassword) throws UserNameAlreadyExistsException {
        if (userRepository.existsByUsername(username)) {
            throw new UserNameAlreadyExistsException(username);
        }
        String hashedPassword = passwordEncoder.encode(plainPassword);
        User user = new User(username, hashedPassword);
        userRepository.save(user);
        return true;
    }


}
