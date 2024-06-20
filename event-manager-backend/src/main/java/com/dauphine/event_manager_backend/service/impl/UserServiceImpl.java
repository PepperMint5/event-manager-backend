package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.repository.EventRepository;
import com.dauphine.event_manager_backend.repository.ParticipationRepository;
import com.dauphine.event_manager_backend.repository.UserRepository;
import com.dauphine.event_manager_backend.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;
    private final EventRepository eventRepository;


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, ParticipationRepository participationRepository, EventRepository eventRepository) {
        this.userRepository = userRepository;
        this.participationRepository = participationRepository;
        this.eventRepository = eventRepository;
    }


    @Override
    public User getUserById(UUID id) throws UserNotFoundByIdException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));

    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundByNameException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundByNameException(username));
    }

    @Override
    public User updateUsername(UUID id, String newUsername) throws UserNotFoundByIdException {
        User user = getUserById(id);
        user.setUsername(newUsername);
        return userRepository.save(user);
    }

    @Override
    public User updatePassword(UUID id, String newPassword) throws UserNotFoundByIdException {
        User user = getUserById(id);
        String hashedPassword = passwordEncoder.encode(newPassword);
        user.setPassword(hashedPassword);
        return userRepository.save(user);
    }

    @Override
    public void deleteUserById(UUID id) throws UserNotFoundByIdException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundByIdException(id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User createUser(User user) {
        user.setId(UUID.randomUUID());
        return userRepository.save(user);
    }

    @Override
    public Boolean getIsParticipating(UUID user_id, UUID event_id) throws UserNotFoundByIdException, EventNotFoundByIdException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new UserNotFoundByIdException(user_id));
        Event event = eventRepository.findById(event_id)
                .orElseThrow(() -> new EventNotFoundByIdException(event_id));
        return participationRepository.existsByUserIdAndEventId(user_id, event_id);
    }

    @Override
    public List<Event> getAllParticipations(UUID id) throws UserNotFoundByIdException {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundByIdException(id));
        return participationRepository.findAllEventsByUserId(id);
    }

    @Override
    public void deleteParticipation(UUID user_id, UUID event_id) throws UserNotFoundByIdException, EventNotFoundByIdException {
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new UserNotFoundByIdException(user_id));
        Event event = eventRepository.findById(event_id)
                .orElseThrow(() -> new EventNotFoundByIdException(event_id));
        participationRepository.deleteByUserIdAndEventId(user_id, event_id);
    }

    // @Override
    // public List<User> getParticipatingFriends(UUID user_id, UUID event_id) throws UserNotFoundByIdException, EventNotFoundByIdException {
    //     User user = userRepository.findById(user_id)
    //             .orElseThrow(() -> new UserNotFoundByIdException(user_id));
    //     Event event = eventRepository.findById(event_id)
    //             .orElseThrow(() -> new EventNotFoundByIdException(event_id));
    // }

}
