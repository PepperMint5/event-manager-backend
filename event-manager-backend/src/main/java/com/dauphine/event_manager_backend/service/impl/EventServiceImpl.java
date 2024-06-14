package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.repository.EventRepository;
import com.dauphine.event_manager_backend.service.EventService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllLikeTitle(String title) {
        return eventRepository.getAllLikeTitle(title);
    }
}
