package com.dauphine.event_manager_backend.controller;
import com.dauphine.event_manager_backend.dto.UserResponse;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByIdException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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
        UserResponse userResponse = new UserResponse(user.getId(), user.getPassword());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Get User by Username")
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) throws UserNotFoundByNameException {
        User user = userService.getUserByUsername(username);
        UserResponse userResponse = new UserResponse(user.getId(), user.getPassword());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Username")
    @PutMapping("/{id}/username")
    public ResponseEntity<UserResponse> updateUsername(@PathVariable UUID id, @RequestParam String newUsername) throws UserNotFoundByIdException {
        User user = userService.updateUsername(id, newUsername);
        UserResponse userResponse = new UserResponse(user.getId(), user.getPassword());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Update Password")
    @PutMapping("/{id}/password")
    public ResponseEntity<UserResponse> updatePassword(@PathVariable UUID id, @RequestParam String newPassword) throws UserNotFoundByIdException {
        User user = userService.updatePassword(id, newPassword);
        UserResponse userResponse = new UserResponse(user.getId(), user.getPassword());
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @Operation(summary = "Delete User by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) throws UserNotFoundByIdException {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
