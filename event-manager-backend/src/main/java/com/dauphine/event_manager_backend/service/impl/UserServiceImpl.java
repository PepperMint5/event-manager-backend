package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.repository.UserRepository;
import com.dauphine.event_manager_backend.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User getUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(null);
    }

    @Override
    public User getUserByUsername(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        return userOptional.orElse(null);
    }

    @Override
    public User updateUsername(UUID id, String newUsername) {
        User user = getUserById(id);
        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(UUID id, String newPassword) {
        User user = getUserById(id);
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }
}
