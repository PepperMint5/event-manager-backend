package com.dauphine.event_manager_backend.service;

import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.model.Event;

import java.util.List;

public interface EventService {
    public List<Event> getAll();

    public List<Event> getAllLikeTitle(String title);
}
