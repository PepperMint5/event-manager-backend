package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.dto.*;
import com.dauphine.event_manager_backend.exceptions.CategoryNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.EventNameAlreadyExistsException;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.Participation;
import com.dauphine.event_manager_backend.model.Review;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.service.EventService;
import com.dauphine.event_manager_backend.dto.EventRequest;
import com.dauphine.event_manager_backend.service.impl.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dauphine.event_manager_backend.dto.EventResponse.ListEventResponse;
import static com.dauphine.event_manager_backend.dto.ReviewResponse.ListReviewResponse;
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
        Event event = eventService.createEvent(EventRequest.getTitle(), EventRequest.getCity(), EventRequest.getAddress(), EventRequest.getDate(), EventRequest.getTime(), EventRequest.getDescription(), EventRequest.getCategory(), EventRequest.getOwner());
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
        Event event = eventService.update(id, EventRequest.getTitle(), EventRequest.getCity(), EventRequest.getAddress(), EventRequest.getDate(), EventRequest.getTime(), EventRequest.getDescription(), EventRequest.getCategory());
        return new ResponseEntity<>(new EventResponse(event), HttpStatus.OK);
    }


    //TODO on l'utilise celui là ??
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

    @PostMapping("/{eventId}/participations/{userId}")
    @Operation(
            summary = "Create participation to event",
            description = "Create a new participation to an event for a user."
    )
    public ResponseEntity<Void> createParticipation(@PathVariable UUID eventId, @PathVariable UUID userId) throws EventNotFoundByIdException, UserNotFoundByIdException {
        Participation participation = eventService.createParticipation(eventId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all passed events")
    @GetMapping("/past-events")
    public ResponseEntity<List<EventResponse>> getAllPassedEvents() {
        List<Event> events = eventService.getAllPassedEvents();
        return ResponseEntity.ok(ListEventResponse(events));
    }

    @Operation(summary = "Get all upcoming events")
    @GetMapping("/upcoming-events")
    public ResponseEntity<List<EventResponse>> getAllUpcomingEvents() {
        List<Event> events = eventService.getAllUpcomingEvents();
        return ResponseEntity.ok(ListEventResponse(events));
    }

    @Operation(summary = "Get all cities that have events")
    @GetMapping("/cities")
    public ResponseEntity<List<String>> getAllCitiesWithEvents() {
        List<String> cities = eventService.getAllCitiesWithEvents();
        return ResponseEntity.ok(cities);
    }

    @Operation(summary = "Get all events in city")
    @GetMapping("/cities/{city}")
    public ResponseEntity<List<EventResponse>> getAllEventsInCity(@PathVariable String city) {
        List<Event> events = eventService.getAllEventsInCity(city);
        return ResponseEntity.ok(ListEventResponse(events));
    }

    @Operation(summary = "Get the information if a user participate or not to an event given a userId and a participantId")
    @GetMapping("/{eventId}/participations/{userId}")
    public ResponseEntity<Boolean> isParticipating(@PathVariable UUID eventId, @PathVariable UUID userId) throws EventNotFoundByIdException, UserNotFoundByIdException {
        boolean isParticipating = eventService.isParticipating(eventId,userId);
        return ResponseEntity.ok(isParticipating);
    }


    @Operation(summary = "Delete a participation given a userId and a participantId")
    @DeleteMapping("/{eventId}/participations/{userId}")
    public ResponseEntity<String> deleteParticipation(@PathVariable UUID eventId, @PathVariable UUID userId) throws EventNotFoundByIdException, UserNotFoundByIdException {
        eventService.deleteParticpation(eventId,userId);
        return new ResponseEntity<>("Participation deleted", HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get all reviews for event")
    @GetMapping("/{id}/reviews")
    public ResponseEntity<List<ReviewResponse>> getAllReviews(@PathVariable UUID id){
        List<Review> reviews = eventService.getAllReviews(id);
        return ResponseEntity.ok(ListReviewResponse(reviews));
    }


    @PostMapping("/{eventId}/reviews")
    @Operation(
            summary = "Create review for event",
            description = "Create a new review for a past event for a user."
    )
    public ResponseEntity<ReviewResponse> createReview(@RequestBody ReviewRequest reviewRequest, @PathVariable UUID eventId) throws EventNotFoundByIdException, UserNotFoundByIdException {
        Review review = eventService.createReview(eventId, reviewRequest.getUserId(), reviewRequest.getComment(), reviewRequest.getGrade());
        return new ResponseEntity<>(new ReviewResponse(review), HttpStatus.OK);
    }

    @GetMapping("/owner/{id}")
    @Operation(
            summary = "Get all events owned by a user endpoint",
            description = "Return all events with owner_id = to a given user_id"
    )
    public ResponseEntity<List<EventResponse>> getAllByOwnerUserId(@PathVariable UUID id) throws UserNotFoundByIdException {
        List<Event> events =  eventService.getAllEventsByOwnerId(id);
        return ResponseEntity.ok(ListEventResponse(events));
    }


    @Operation(summary = "Get all upcoming events a user participate to by userId")
    @GetMapping("upcoming-events/participations/user/{id}")
    public ResponseEntity<List<EventResponse>> getAllUpcomingEventFromUserId(@PathVariable UUID id) throws UserNotFoundByIdException {
        System.out.println("controller id : " + id);
        List<Event> events =eventService.getAllUpcomingEventsByUserIdParticipation(id);
        return new ResponseEntity<>(ListEventResponse(events), HttpStatus.OK);
    }


    @Operation(summary = "Get all past events a user participate to by userId")
    @GetMapping("past-events/participations/user/{id}")
    public ResponseEntity<List<EventResponse>> getAllPastEventFromUserId(@PathVariable UUID id) throws UserNotFoundByIdException {
        List<Event> events =eventService.getAllPastEventsByUserIdParticipation(id);
        return new ResponseEntity<>(ListEventResponse(events), HttpStatus.OK);
    }



}







