package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    public List<Event> getAll();

    public List<Event> getAllLikeTitle(String title);

    Optional<Event> getEventById(UUID id);

    Event create(String title, String city, String address ,LocalDateTime date, String description, UUID categoryId, UUID userId);

    void deleteById(UUID userId, UUID id);

    Event update(UUID eventId, String title, String city, String address ,LocalDateTime date, String description, UUID categoryId, UUID userId);
}
