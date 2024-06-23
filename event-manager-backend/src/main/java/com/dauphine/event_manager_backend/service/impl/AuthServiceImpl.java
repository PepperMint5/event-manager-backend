package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.exceptions.*;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.repository.UserRepository;
import com.dauphine.event_manager_backend.service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @Override
    public User login(String username, String password)  throws UserNotFoundByNameException  {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user; // Connexion succeed
            }
        }
        return null; // Username or password incorrect
    }

    @Override
    public User registerUser(String username, String plainPassword) throws UserNameAlreadyExistsException {
        if (userRepository.existsByUsername(username)) {
            throw new UserNameAlreadyExistsException(username);
        }
        String hashedPassword = passwordEncoder.encode(plainPassword);
        User user = new User(username, hashedPassword);
        userRepository.save(user);
        return user;
    }


}
