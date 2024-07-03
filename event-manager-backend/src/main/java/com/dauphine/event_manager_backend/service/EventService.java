package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.EventNameAlreadyExistsException;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Participation;
import com.dauphine.event_manager_backend.model.Review;
import com.dauphine.event_manager_backend.model.User;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface EventService {

    List<Event> getAll();

    /**
     *
     * @param title
     * @return
     */
    List<Event> getAllLikeTitle(String title);

    /**
     *
     * @param id
     * @return
     * @throws EventNotFoundByIdException
     */
    Event getEventById(UUID id) throws EventNotFoundByIdException;

    /**
     *
     * @param title
     * @param city
     * @param address
     * @param date
     * @param time
     * @param description
     * @param categoryId
     * @param userId
     * @return
     * @throws CategoryNotFoundByIdException
     * @throws UserNotFoundByIdException
     * @throws EventNameAlreadyExistsException
     */
    Event createEvent(String title, String city, String address, Date date, LocalTime time, String description, UUID categoryId, UUID userId) throws CategoryNotFoundByIdException, UserNotFoundByIdException, EventNameAlreadyExistsException;

    /**
     *
     * @param id
     * @throws EventNotFoundByIdException
     */
    void deleteById(UUID id) throws EventNotFoundByIdException;

    /**
     *
     * @param eventId
     * @param title
     * @param city
     * @param address
     * @param date
     * @param time
     * @param description
     * @param categoryId
     * @return
     * @throws EventNotFoundByIdException
     * @throws CategoryNotFoundByIdException
     * @throws EventNameAlreadyExistsException
     */
    Event update(UUID eventId, String title, String city, String address, Date date, LocalTime time, String description, UUID categoryId) throws EventNotFoundByIdException, CategoryNotFoundByIdException, EventNameAlreadyExistsException;

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundByIdException
     */
    List<Event> getAllLikeUserId(UUID id) throws UserNotFoundByIdException;

    /**
     *
     * @param id
     * @return
     * @throws CategoryNotFoundByIdException
     */
    List<Event> getAllLikeCategoryId(UUID id) throws CategoryNotFoundByIdException;

    /**
     *
     * @param id
     * @return
     * @throws EventNotFoundByIdException
     */
    List<User> getAllUsersByEventId(UUID id) throws EventNotFoundByIdException;

    /**
     *
     * @param id
     * @return
     * @throws EventNotFoundByIdException
     */
    int getNumberOfUsersByEventId(UUID id) throws EventNotFoundByIdException;

    /**
     *
     * @param event_id
     * @param user_id
     * @return
     * @throws EventNotFoundByIdException
     * @throws UserNotFoundByIdException
     */
    Participation createParticipation(UUID event_id, UUID user_id) throws EventNotFoundByIdException, UserNotFoundByIdException;

    /**
     *
     * @return
     */
    List<Event> getAllPassedEvents();

    /**
     *
     * @return
     */
    List<Event> getAllUpcomingEvents();

    /**
     *
     * @return
     */
    List<String> getAllCitiesWithEvents();

    /**
     *
     * @param city
     * @return
     */
    List<Event> getAllEventsInCity(String city);

    /**
     *
     * @param eventId
     * @param userId
     * @return
     * @throws EventNotFoundByIdException
     * @throws UserNotFoundByIdException
     */
    boolean isParticipating(UUID eventId, UUID userId) throws EventNotFoundByIdException, UserNotFoundByIdException;

    /**
     *
     * @param eventId
     * @param userId
     * @throws UserNotFoundByIdException
     * @throws EventNotFoundByIdException
     */
    void deleteParticpation(UUID eventId, UUID userId) throws UserNotFoundByIdException, EventNotFoundByIdException;

    /**
     *
     * @param id
     * @return
     */
    List<Review> getAllReviews(UUID id);

    /**
     *
     * @param event_id
     * @param user_id
     * @param comment
     * @param grade
     * @return
     * @throws EventNotFoundByIdException
     * @throws UserNotFoundByIdException
     */
    Review createReview(UUID event_id, UUID user_id, String comment, int grade) throws EventNotFoundByIdException, UserNotFoundByIdException;

    /**
     *
     * @param userId
     * @return
     * @throws UserNotFoundByIdException
     */
    List<Event> getAllUpcomingEventsByUserIdParticipation(UUID userId) throws UserNotFoundByIdException;

    /**
     *
     * @param id
     * @return
     * @throws UserNotFoundByIdException
     */
    List<Event> getAllPastEventsByUserIdParticipation(UUID id) throws UserNotFoundByIdException;

    /**
     *
     * @param ownerId
     * @return
     * @throws UserNotFoundByIdException
     */
    List<Event> getAllEventsByOwnerId(UUID ownerId) throws UserNotFoundByIdException;
}
