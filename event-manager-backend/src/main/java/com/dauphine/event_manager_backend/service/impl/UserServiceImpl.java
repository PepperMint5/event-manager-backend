package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.FriendshipAlreadyExistException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Friendship;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.repository.EventRepository;
import com.dauphine.event_manager_backend.repository.FriendshipRepository;
import com.dauphine.event_manager_backend.repository.ParticipationRepository;
import com.dauphine.event_manager_backend.repository.UserRepository;
import com.dauphine.event_manager_backend.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;
    private final EventRepository eventRepository;

    private final FriendshipRepository friendshipRepository;


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRepository, ParticipationRepository participationRepository, EventRepository eventRepository, FriendshipRepository friendshipRepository) {
        this.userRepository = userRepository;
        this.participationRepository = participationRepository;
        this.eventRepository = eventRepository;
        this.friendshipRepository = friendshipRepository;
    }


    @Override
    public User getUserById(UUID id) throws UserNotFoundByIdException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundByIdException(id));

    }

    @Override
    public List<User> getUsersFriendsById(UUID id){
        return userRepository.findFriendsById(id);
    }


    @Override
    @Transactional
    public void createFriendship(UUID id1, UUID id2) throws UserNotFoundByIdException, FriendshipAlreadyExistException {
        User user1 = userRepository.findById(id1).orElseThrow(() -> new UserNotFoundByIdException(id1));
        User user2 = userRepository.findById(id2).orElseThrow(() -> new UserNotFoundByIdException(id2));
        if (friendshipRepository.existsByUser1AndUser2(user1, user2) || friendshipRepository.existsByUser1AndUser2(user2, user1)) {
            throw new FriendshipAlreadyExistException(user2);
        }
        friendshipRepository.save(new Friendship(user1, user2));
    }

    @Override
    public void deleteFriendship(UUID id1, UUID id2) throws UserNotFoundByIdException{
        User user1 = userRepository.findById(id1).orElseThrow(() -> new UserNotFoundByIdException(id1));
        User user2 = userRepository.findById(id2).orElseThrow(() -> new UserNotFoundByIdException(id2));
        Friendship friendship1 = new Friendship(user1, user2);
        Friendship friendship2 = new Friendship(user2, user1);
        friendshipRepository.delete(friendship1);
        friendshipRepository.delete(friendship2);
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
        if (!userRepository.existsById(user_id)) {
            throw new UserNotFoundByIdException(user_id);
        }
        if (!eventRepository.existsById(event_id)) {
            throw new EventNotFoundByIdException(event_id);
        }
        return participationRepository.existsByUserIdAndEventId(user_id, event_id);
    }

    @Override
    public List<Event> getAllParticipations(UUID id) throws UserNotFoundByIdException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundByIdException(id);
        }
        return participationRepository.findAllEventsByUserId(id);
    }

    // @Override
    // public List<User> getParticipatingFriends(UUID user_id, UUID event_id) throws UserNotFoundByIdException, EventNotFoundByIdException {
    //     User user = userRepository.findById(user_id)
    //             .orElseThrow(() -> new UserNotFoundByIdException(user_id));
    //     Event event = eventRepository.findById(event_id)
    //             .orElseThrow(() -> new EventNotFoundByIdException(event_id));
    // }

    @Override
    public List<UUID> getEventsByFriendsParticipations(UUID userId) throws UserNotFoundByIdException{
        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundByIdException(userId);
        }
        List<User> friends = userRepository.findFriendsById(userId);
        Set<UUID> eventIds = new HashSet<>();
        for (User friend : friends) {
            List<UUID> friendEventIds = participationRepository.findAllEventIdsByUserId(friend.getId());
            eventIds.addAll(friendEventIds);
        }
        return new ArrayList<>(eventIds);
    }

}
