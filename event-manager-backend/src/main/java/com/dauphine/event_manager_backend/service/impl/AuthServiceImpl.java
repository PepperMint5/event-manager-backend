package com.dauphine.event_manager_backend.service.impl;

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
    public User login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user; // Connexion succeed
            }
        }
        return null; // Username or password incorrect
        //TODO ajouter une exception username already taken et incorret password (peut être dans une seule et même exception
    }

    @Override
    public boolean registerUser(String username, String plainPassword) {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isPresent()) {
            return false;
            //TODO ajouter une exception UsernameAlreadyExists
        }
        String hashedPassword = passwordEncoder.encode(plainPassword);
        User user = new User(username, hashedPassword);
        userRepository.save(user);
        return true;
    }


}
