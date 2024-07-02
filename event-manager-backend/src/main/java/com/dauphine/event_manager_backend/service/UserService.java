package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.FriendshipAlreadyExistException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User getUserById(UUID id) throws UserNotFoundByIdException;

    List<User> getUsersFriendsById(UUID id) ;

    void createFriendship(UUID id1, UUID id2) throws UserNotFoundByIdException, FriendshipAlreadyExistException;

    void deleteFriendship(UUID id1, UUID id2) throws UserNotFoundByIdException;
    User getUserByUsername(String username) throws UserNotFoundByNameException;

    User updateUsername(UUID id, String newUsername) throws UserNotFoundByIdException;

    User updatePassword(UUID id, String newPassword) throws UserNotFoundByIdException;

    void deleteUserById(UUID id) throws UserNotFoundByIdException;

    User createUser(User user);

    Boolean getIsParticipating(UUID user_id, UUID event_id) throws UserNotFoundByIdException, EventNotFoundByIdException;

    List<Event> getAllParticipations(UUID id) throws UserNotFoundByIdException;

    List<UUID> getEventsByFriendsParticipations(UUID userId) throws UserNotFoundByIdException;

    //List<User> getParticipatingFriends(UUID user_id, UUID event_id) throws UserNotFoundByIdException, EventNotFoundByIdException;

}
