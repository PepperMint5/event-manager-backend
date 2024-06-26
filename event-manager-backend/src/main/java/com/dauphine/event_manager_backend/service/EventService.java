package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.EventNameAlreadyExistsException;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Participation;
import com.dauphine.event_manager_backend.model.User;

import java.sql.Date;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface EventService {
    List<Event> getAll();

    List<Event> getAllLikeTitle(String title);

    Event getEventById(UUID id) throws EventNotFoundByIdException;

    Event create(String title, String city, String address , Date date, LocalTime time, String description, UUID categoryId, UUID userId) throws CategoryNotFoundByIdException, UserNotFoundByIdException, EventNameAlreadyExistsException;

    void deleteById(UUID id) throws EventNotFoundByIdException;

    Event update(UUID eventId, String title, String city, String address , Date date, LocalTime time, String description, UUID categoryId) throws EventNotFoundByIdException, CategoryNotFoundByIdException, EventNameAlreadyExistsException;

    List<Event> getAllLikeUserId(UUID id) throws UserNotFoundByIdException;

    List<Event> getAllLikeCategoryId(UUID id) throws CategoryNotFoundByIdException;

    List<User> getAllUsersByEventId(UUID id) throws EventNotFoundByIdException;

    int getNumberOfUsersByEventId(UUID id) throws EventNotFoundByIdException;

    Participation createParticipation(UUID event_id, UUID user_id) throws EventNotFoundByIdException, UserNotFoundByIdException;

    List<Event> getAllPassedEvents();

    List<Event> getAllUpcomingEvents();
}
