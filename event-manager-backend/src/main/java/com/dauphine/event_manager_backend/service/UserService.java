package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.FriendshipAlreadyExistException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Review;
import com.dauphine.event_manager_backend.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundByIdException
     */
    User getUserById(UUID id) throws UserNotFoundByIdException;

    /**
     *
     * @param id
     * @return
     */
    List<User> getUsersFriendsById(UUID id);

    /**
     *
     * @param id1
     * @param id2
     * @throws UserNotFoundByIdException
     * @throws FriendshipAlreadyExistException
     */
    void createFriendship(UUID id1, UUID id2) throws UserNotFoundByIdException, FriendshipAlreadyExistException;

    /**
     *
     * @param id1
     * @param id2
     * @throws UserNotFoundByIdException
     */
    void deleteFriendship(UUID id1, UUID id2) throws UserNotFoundByIdException;

    /**
     *
     * @param username
     * @return
     * @throws UserNotFoundByNameException
     */
    User getUserByUsername(String username) throws UserNotFoundByNameException;

    /**
     *
     * @param id
     * @param newUsername
     * @return
     * @throws UserNotFoundByIdException
     */
    User updateUsername(UUID id, String newUsername) throws UserNotFoundByIdException;

    /**
     *
     * @param id
     * @param newPassword
     * @return
     * @throws UserNotFoundByIdException
     */
    User updatePassword(UUID id, String newPassword) throws UserNotFoundByIdException;

    /**
     *
     * @param id
     * @throws UserNotFoundByIdException
     */
    void deleteUserById(UUID id) throws UserNotFoundByIdException;

    /**
     *
     * @param user_id
     * @param event_id
     * @return
     * @throws UserNotFoundByIdException
     * @throws EventNotFoundByIdException
     */
    Boolean getIsParticipating(UUID user_id, UUID event_id) throws UserNotFoundByIdException, EventNotFoundByIdException;

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundByIdException
     */
    List<Event> getAllParticipations(UUID id) throws UserNotFoundByIdException;

    /**
     *
     * @param userId
     * @return
     * @throws UserNotFoundByIdException
     */
    List<UUID> getEventsByFriendsParticipations(UUID userId) throws UserNotFoundByIdException;

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundByIdException
     */
    List<Review> getAllReviewsByUser(UUID id) throws UserNotFoundByIdException;
}
