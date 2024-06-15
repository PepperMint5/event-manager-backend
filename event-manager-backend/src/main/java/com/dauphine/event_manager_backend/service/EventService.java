package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.model.Event;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EventService {
    public List<Event> getAll();

    Optional<Event> getById(UUID id);
}
