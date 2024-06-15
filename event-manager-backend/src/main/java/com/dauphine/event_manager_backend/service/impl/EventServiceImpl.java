package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.repository.EventRepository;
import com.dauphine.event_manager_backend.service.EventService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class EventServiceImpl implements EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository repository) {
        this.eventRepository = repository;
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.findAll();
    }

    @Override
    public Optional<Event> getById(UUID id) {
        return eventRepository.findById(id);
    }
}
