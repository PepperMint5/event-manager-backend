package com.dauphine.event_manager_backend.service.impl;

import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.EventNameAlreadyExistsException;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Category;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Participation;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.repository.CategoryRepository;
import com.dauphine.event_manager_backend.repository.EventRepository;
import com.dauphine.event_manager_backend.repository.ParticipationRepository;
import com.dauphine.event_manager_backend.repository.UserRepository;
import com.dauphine.event_manager_backend.service.EventService;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;
    private final ParticipationRepository participationRepository;

    public EventServiceImpl(EventRepository eventRepository, CategoryRepository categoryRepository, UserRepository userRepository, ParticipationRepository participationRepository) {
        this.eventRepository = eventRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.participationRepository = participationRepository;
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
    public Event create(String title, String city, String address , Date date, LocalTime time, String description, UUID categoryId, UUID userId) throws CategoryNotFoundByIdException, UserNotFoundByIdException, EventNameAlreadyExistsException {
        Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new CategoryNotFoundByIdException(categoryId));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundByIdException(userId));
        if (eventRepository.existsByTitle(title)) {
            throw new EventNameAlreadyExistsException(title);
        }
        LocalDateTime last_updated = LocalDateTime.now();
        Event newEvent = new Event(title, city, address, date, time, description, last_updated, category, user);
        return eventRepository.save(newEvent);
    }

    @Override
    public void deleteById(UUID id) throws EventNotFoundByIdException {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundByIdException(id);
        }
        eventRepository.deleteById(id);
    }

    @Override
    public Event update(UUID eventId, String title, String city, String address, Date date, LocalTime time, String description, UUID categoryId) throws EventNotFoundByIdException, CategoryNotFoundByIdException, EventNameAlreadyExistsException {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundByIdException(categoryId));
        Event event = getEventById(eventId);
        if (eventRepository.existsByTitle(title) && !Objects.equals(event.getTitle(), title)) {
            throw new EventNameAlreadyExistsException(title);
        }
        event.setTitle(title);
        event.setCity(city);
        event.setAddress(address);
        event.setDate(date);
        event.setTime(time);
        event.setDescription(description);
        event.setLastUpdated(LocalDateTime.now());
        event.setCategory(category);
        return eventRepository.save(event);
    }

    @Override
    public List<Event> getAllLikeUserId(UUID id) throws UserNotFoundByIdException {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundByIdException(id);
        }
        return eventRepository.getAllLikeUserId(id);
    }

    @Override
    public List<Event> getAllLikeCategoryId(UUID id) throws CategoryNotFoundByIdException {
        if (!categoryRepository.existsById(id)) {
            throw new CategoryNotFoundByIdException(id);
        }
        return eventRepository.getAllLikeCategoryId(id);
    }

    @Override
    public List<User> getAllUsersByEventId(UUID id) throws EventNotFoundByIdException {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundByIdException(id);
        }
        return participationRepository.findAllUsersByEventId(id);
    }

    @Override
    public int getNumberOfUsersByEventId(UUID id) throws EventNotFoundByIdException {
        if (!eventRepository.existsById(id)) {
            throw new EventNotFoundByIdException(id);
        }
        return participationRepository.countNumberOfUsersByEventId(id);
    }

    @Override
    public Participation createParticipation(UUID event_id, UUID user_id) throws EventNotFoundByIdException, UserNotFoundByIdException {
        Event event = eventRepository.findById(event_id)
                .orElseThrow(() -> new EventNotFoundByIdException(event_id));
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new UserNotFoundByIdException(user_id));
        Participation participation = new Participation(user, event);
        return participationRepository.save(participation);
    }

    @Override
    public List<Event> getAllPassedEvents() {
        return eventRepository.getAllPassedEvents();
    }
    @Override
    public List<Event> getAllUpcomingEvents() {
        return eventRepository.getAllUpcomingEvents();
    }

}
