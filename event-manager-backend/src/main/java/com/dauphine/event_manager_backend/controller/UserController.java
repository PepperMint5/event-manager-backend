package com.dauphine.event_manager_backend.controller;
import com.dauphine.event_manager_backend.dto.EventResponse;
import com.dauphine.event_manager_backend.dto.UserResponse;
import com.dauphine.event_manager_backend.exceptions.EventNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.Event;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static com.dauphine.event_manager_backend.dto.EventResponse.ListEventResponse;

@RestController
@Tag(name = "User Controller",
        description = "Operations related to User management")
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get User by ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID id) throws UserNotFoundByIdException {
        User user = userService.getUserById(id);
        UserResponse userResponse = new UserResponse(user.getId(), user.getUsername());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get User by Username")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) throws UserNotFoundByNameException {
        User user = userService.getUserByUsername(username);
        UserResponse userResponse = new UserResponse(user.getId(), user.getUsername());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Username")
    @PutMapping("/{id}/username")
    public ResponseEntity<UserResponse> updateUsername(@PathVariable UUID id, @RequestParam String newUsername) throws UserNotFoundByIdException {
        User user = userService.updateUsername(id, newUsername);
        UserResponse userResponse = new UserResponse(user.getId(), user.getUsername());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Password")
    @PutMapping("/{id}/password")
    public ResponseEntity<UserResponse> updatePassword(@PathVariable UUID id, @RequestParam String newPassword) throws UserNotFoundByIdException {
        User user = userService.updatePassword(id, newPassword);
        UserResponse userResponse = new UserResponse(user.getId(), user.getUsername());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    //@Operation(summary = "Create New User")
    //@PostMapping
    //public ResponseEntity<UserResponse> createUser(@RequestBody User user) {
    //    User createdUser = userService.createUser(user);
    //    UserResponse userResponse = new UserResponse(createdUser);
    //    return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    //}

    @Operation(summary = "Delete User by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) throws UserNotFoundByIdException {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Get if User is participating to an event")
    @GetMapping("/{userId}/event")
    public ResponseEntity<Boolean> getIsParticipating(@PathVariable UUID userId, @RequestParam UUID eventId) throws UserNotFoundByIdException, EventNotFoundByIdException {
        return new ResponseEntity<>(userService.getIsParticipating(userId, eventId), HttpStatus.OK);
    }

    @Operation(summary = "Get all user's participations")
    @GetMapping("/{id}/participations")
    public ResponseEntity<List<EventResponse>> getAllParticipations(@PathVariable UUID id) throws UserNotFoundByIdException {
        List<Event> events = userService.getAllParticipations(id);
        return new ResponseEntity<>(ListEventResponse(events), HttpStatus.OK);
    }

    @Operation(summary = "Delete participation to an event")
    @DeleteMapping("/{userId}/event")
    public ResponseEntity<Void> deleteParticipation(@PathVariable UUID userId, @RequestParam UUID eventId) throws EventNotFoundByIdException, UserNotFoundByIdException {
        userService.deleteParticipation(userId, eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //@Operation(summary = "Get friends participating to same event")
    //@GetMapping("/{id}/event/friends")
    //public ResponseEntity<List<UserResponse>> getParticipatingFriends(@PathVariable UUID id, @RequestParam UUID eventId) throws EventNotFoundByIdException, UserNotFoundByIdException {
    //    List<User> users = userService.getParticipatingFriends(id, eventId);
    //    return new ResponseEntity<>(ListUserResponse(users), HttpStatus.OK);
    //}


}
