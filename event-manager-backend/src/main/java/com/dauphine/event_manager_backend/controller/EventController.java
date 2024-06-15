package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(
        name = "Event Controller",
        description = "Operations related to Event management"
)
@RequestMapping("/v1/events")
public class EventController {

    private EventService eventService;

    @GetMapping
    @Operation(
            summary = "Get all events endpoint",
            description = "Return all events"
    )
    public ResponseEntity<List<Event>> getAll()  {
        List<Event> events = eventService.getAll();
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
