package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.repository.CategoryRepository;
import com.dauphine.event_manager_backend.repository.EventRepository;
import com.dauphine.event_manager_backend.repository.UserRepository;
import com.dauphine.event_manager_backend.service.EventService;
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
    public Event getEventById(UUID id) throws EventNotFoundByIdException {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundByIdException(id));
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
    public void deleteById(UUID id) throws EventNotFoundByIdException {
        getEventById(id);
        eventRepository.deleteById(id);
    }

    @Override
    public Event update(UUID eventId, String title, String city, String address ,LocalDateTime date, String description, UUID categoryId) throws EventNotFoundByIdException, CategoryNotFoundByIdException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundByIdException(categoryId));
        Event event = getEventById(eventId);
        event.setTitle(title);
        event.setCity(city);
        event.setAddress(address);
        event.setDate(date);
        event.setDescription(description);
        event.setCategory(category);
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllLikeUserId(UUID id) {
        return eventRepository.getAllLikeUserId(id);
    }

    @Override
    public List<Event> getAllLikeCategoryId(UUID id) {
        return eventRepository.getAllLikeCategoryId(id);
    }
}
