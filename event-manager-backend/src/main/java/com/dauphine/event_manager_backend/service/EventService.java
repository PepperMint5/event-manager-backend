package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface EventService {
    List<Event> getAll();

    List<Event> getAllLikeTitle(String title);

    Event getEventById(UUID id) throws EventNotFoundByIdException;

    Event create(String title, String city, String address ,LocalDateTime date, String description, UUID categoryId, UUID userId);

    void deleteById(UUID id) throws EventNotFoundByIdException;

    Event update(UUID eventId, String title, String city, String address, LocalDateTime date, String description, UUID categoryId) throws EventNotFoundByIdException, CategoryNotFoundByIdException;

    List<Event> getAllLikeUserId(UUID id);

    List<Event> getAllLikeCategoryId(UUID id);
}
