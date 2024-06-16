package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.repository.CategoryRepository;
import com.dauphine.event_manager_backend.repository.EventRepository;
import com.dauphine.event_manager_backend.repository.UserRepository;
import com.dauphine.event_manager_backend.service.EventService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Event> getAll(){
        return eventRepository.findAll();
    }

    @Override
    public List<Event> getAllLikeTitle(String title) {
        return eventRepository.getAllLikeTitle(title);
    }

    @Override
    public Optional<Event> getEventById(UUID id) {
        return eventRepository.findById(id);
    }

    @Override
    public Event create(String title, String city, String address , LocalDateTime date, String description, UUID categoryId, UUID userId) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        Optional<User> optionalUser =userRepository.findById(userId);
        if (optionalCategory.isPresent() && optionalUser.isPresent()) {
            Category category = optionalCategory.get();
            User user = optionalUser.get();
            Event newEvent = new Event(title, city, address, date, description, category, user);
            return eventRepository.save(newEvent);
        }
        else if (optionalCategory.isEmpty()) {
            throw new RuntimeException("Category with following id not found : " + categoryId);
        }
        else {
            throw new RuntimeException("User with following id not found : " + userId);
        }
    }


    @Override
    public void deleteById(UUID userId, UUID id) {
        Optional<Event> optionalEvent = getEventById(id);
            if (optionalEvent.isPresent()) {
                if (userId != optionalEvent.get().getId()) {
                    eventRepository.deleteById(id);
                    return;
                }
                else {
                    throw new RuntimeException("User not authorized to delete");
                }
            } else {
                throw new RuntimeException("Event with following id not found : " + id);
            }
    }


    @Override
    public Event update(UUID eventId, String title, String city, String address ,LocalDateTime date, String description, UUID categoryId, UUID userId) {
        Optional<Event> optionalEvent = getEventById(categoryId);
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (optionalEvent.isPresent() && optionalCategory.isPresent()) {
            Event event = optionalEvent.get();
            event.setTitle(title);
            event.setCity(city);
            event.setAddress(address);
            event.setDate(date);
            event.setDescription(description);
            event.setCategory(optionalCategory.get());
            return event;
        }
        else if (optionalEvent.isEmpty()) {
            throw new RuntimeException("Event with following id not found : " + eventId);
        }
        else {
            throw new RuntimeException("Category with following id not found : " + categoryId);
        }
    }
}
