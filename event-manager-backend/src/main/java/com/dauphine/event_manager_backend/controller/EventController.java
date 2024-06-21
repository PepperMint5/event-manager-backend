package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.dto.EventResponse;
import com.dauphine.event_manager_backend.dto.ParticipationResponse;
import com.dauphine.event_manager_backend.dto.UserResponse;
import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.EventNameAlreadyExistsException;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Participation;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.service.EventService;
import com.dauphine.event_manager_backend.dto.EventRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dauphine.event_manager_backend.dto.EventResponse.ListEventResponse;
import static com.dauphine.event_manager_backend.dto.UserResponse.ListUserResponse;

@RestController
@Tag(
        name = "Event API",
        description = "Endpoints for events"
)
@RequestMapping("/v1/events")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    @Operation(
            summary = "Get all events endpoint",
            description = "Return all events, or all with a name like {name}"
    )
    public ResponseEntity<List<EventResponse>> getAll(@RequestParam(required = false) String title)  {
        List<Event> events = title == null | (title!=null && title.isBlank())
                ? eventService.getAll()
                : eventService.getAllLikeTitle(title);
        return ResponseEntity.ok(ListEventResponse(events));
    }

    @Operation(summary = "Get Event by ID")
    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getEventById(@PathVariable UUID id) throws EventNotFoundByIdException {
        Event event = eventService.getEventById(id);
        return new ResponseEntity<>(new EventResponse(event), HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Create event endpoint",
            description = "Create a new event based on {EventRequest} data. Returns the created event"
    )
    public ResponseEntity<EventResponse> createEvent(@RequestBody EventRequest EventRequest) throws CategoryNotFoundByIdException, EventNameAlreadyExistsException, UserNotFoundByIdException {
        Event event = eventService.create(EventRequest.getTitle(), EventRequest.getCity(), EventRequest.getAddress(), EventRequest.getDate(), EventRequest.getDescription(), EventRequest.getCategoryId(), EventRequest.getUserId());
        return new ResponseEntity<>(new EventResponse(event), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete event by id endpoint",
            description = "Delete event by {id}. Returns {id}"
    )
    public ResponseEntity<Void> deleteEventById(@PathVariable UUID id) throws EventNotFoundByIdException {
        eventService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update event by id endpoint",
            description = "Update event by {id}. Returns {id}"
    )
    public ResponseEntity<EventResponse> updateEventById(@PathVariable UUID id, @RequestBody EventRequest EventRequest) throws EventNotFoundByIdException, CategoryNotFoundByIdException, EventNameAlreadyExistsException {
        Event event = eventService.update(id, EventRequest.getTitle(), EventRequest.getCity(), EventRequest.getAddress(), EventRequest.getDate(), EventRequest.getDescription(), EventRequest.getCategoryId());
        return new ResponseEntity<>(new EventResponse(event), HttpStatus.OK);
    }

    @Operation(summary = "Get all Events by user ID")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<EventResponse>> getEventsByUserId(@PathVariable UUID id) throws UserNotFoundByIdException {
        List<Event> events = eventService.getAllLikeUserId(id);
        return new ResponseEntity<>(ListEventResponse(events), HttpStatus.OK);
    }

    @Operation(summary = "Get all Events by category ID")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<EventResponse>> getEventsByCategoryId(@PathVariable UUID id) throws CategoryNotFoundByIdException {
        List<Event> events = eventService.getAllLikeCategoryId(id);
        return new ResponseEntity<>(ListEventResponse(events), HttpStatus.OK);
    }

    @Operation(summary = "Get all participants")
    @GetMapping("/{id}/participants")
    public ResponseEntity<List<UserResponse>> getAllParticipants(@PathVariable UUID id) throws EventNotFoundByIdException {
        List<User> users = eventService.getAllUsersByEventId(id);
        return new ResponseEntity<>(ListUserResponse(users), HttpStatus.OK);
    }

    @Operation(summary = "Get number of participants")
    @GetMapping("/{id}/count")
    public ResponseEntity<Integer> getNumberOfParticipants(@PathVariable UUID id) throws EventNotFoundByIdException {
        int nbParticipants = eventService.getNumberOfUsersByEventId(id);
        return new ResponseEntity<>(nbParticipants, HttpStatus.OK);
    }

    @PostMapping("/{eventId}/{userId}")
    @Operation(
            summary = "Create participation to event",
            description = "Create a new participation to an event for a user."
    )
    public ResponseEntity<ParticipationResponse> createParticipation(@PathVariable UUID eventId, @PathVariable UUID userId) throws EventNotFoundByIdException, UserNotFoundByIdException {
        Participation participation = eventService.createParticipation(eventId, userId);
        return new ResponseEntity<>(new ParticipationResponse(participation), HttpStatus.OK);
    }

}
