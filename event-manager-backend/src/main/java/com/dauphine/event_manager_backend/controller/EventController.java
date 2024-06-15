package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    //@Operation(summary = "Get Event by ID")
    //@GetMapping("/{id}")
    //public ResponseEntity<?> getEventById(@PathVariable UUID id) {
    //    Event event = eventService.getEventById(id);
    //    if (event != null) {
    //        return new ResponseEntity<>(event, HttpStatus.OK);
    //    } else {
    //        return new ResponseEntity<>("Event not found", HttpStatus.NOT_FOUND);
    //    }
    //}
}
