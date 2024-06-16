package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.service.EventService;
import com.dauphine.event_manager_backend.dto.EventRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<List<Event>> getAll(@RequestParam(required = false) String title)  {
        List<Event> events = title == null | (title!=null && title.isBlank())
                ? eventService.getAll()
                : eventService.getAllLikeTitle(title);
        return ResponseEntity.ok(events);
    }

    @Operation(summary = "Get Event by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable UUID id) throws EventNotFoundByIdException {
        return new ResponseEntity<>(eventService.getEventById(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(
            summary = "Create event endpoint",
            description = "Create a new event based on {EventRequest} data. Returns the created event"
    )
    public Event createEvent(@RequestBody EventRequest EventRequest){
        return eventService.create(EventRequest.getTitle(), EventRequest.getCity(), EventRequest.getAddress(), EventRequest.getDate(), EventRequest.getDescription(), EventRequest.getCategoryId(), EventRequest.getUserId());
    }

    @DeleteMapping("/delete/{id}")
    @Operation(
            summary = "Delete event by id endpoint",
            description = "Delete event by {id}. Returns {id}"
    )
    public ResponseEntity<Void> deleteEventById(@PathVariable UUID id) throws EventNotFoundByIdException {
        eventService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update event by id endpoint",
            description = "Update event by {id}. Returns {id}"
    )
    public ResponseEntity<Event> updateEventById(@PathVariable UUID id, @RequestBody EventRequest EventRequest) throws EventNotFoundByIdException, CategoryNotFoundByIdException {
        Event updatedEvent = eventService.update(id, EventRequest.getTitle(), EventRequest.getCity(), EventRequest.getAddress(), EventRequest.getDate(), EventRequest.getDescription(), EventRequest.getCategoryId(), EventRequest.getUserId());
        return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @Operation(summary = "Get all Events by user ID")
    @GetMapping("/user/{id}")
    public ResponseEntity<List<Event>> getEventsByUserId(@PathVariable UUID id) {
        return new ResponseEntity<>(eventService.getAllLikeUserId(id), HttpStatus.OK);
    }

    @Operation(summary = "Get all Events by category ID")
    @GetMapping("/category/{id}")
    public ResponseEntity<List<Event>> getEventsByCategoryId(@PathVariable UUID id) {
        return new ResponseEntity<>(eventService.getAllLikeCategoryId(id), HttpStatus.OK);
    }
}
