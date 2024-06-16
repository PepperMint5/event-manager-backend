package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.service.EventService;
import com.dauphine.event_manager_backend.dto.EventRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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

    // TODO : CHANGE ALL THE RESONSE ENTITY : put a type, all ifs are to be is service, look
    // at blogger box for inspo if needed
    @Operation(summary = "Get Event by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable UUID id) {
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            return new ResponseEntity<>(event.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping
    @Operation(
            summary = "Post event endpoint",
            description = "Create a new event based on {EventRequest} data. Returns the created event"
    )
    public Event createEvent(@RequestBody EventRequest EventRequest){
        return eventService.create(EventRequest.getTitle(), EventRequest.getCity(), EventRequest.getAddress(), EventRequest.getDate(), EventRequest.getDescription(), EventRequest.getCategoryId(), EventRequest.getUserId());
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete event by id endpoint",
            description = "Delete event by {id}. Returns {id}"
    )
    public ResponseEntity<?> deleteEventById(@RequestBody UUID userId, @PathVariable UUID id){
        Optional<Event> event = eventService.getEventById(id);
        if (event.isPresent()) {
            eventService.deleteById(userId, id);
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Delete event by id endpoint",
            description = "Delete event by {id}. Returns {id}"
    )
    public ResponseEntity<?> updateEventById(@PathVariable UUID id, @RequestBody EventRequest EventRequest){
        Optional<Event> optionalEvent = eventService.getEventById(id);
        if (optionalEvent.isPresent() && optionalEvent.get().getUser().getId() == userId) {
            eventService.update(id, EventRequest.getTitle(), EventRequest.getCity(), EventRequest.getAddress(), EventRequest.getDate(), EventRequest.getDescription(), EventRequest.getCategoryId(), EventRequest.getUserId());
            return new ResponseEntity<>(event, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
        }
}
