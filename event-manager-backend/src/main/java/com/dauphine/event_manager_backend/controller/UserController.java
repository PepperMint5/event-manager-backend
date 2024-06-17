package com.dauphine.event_manager_backend.controller;
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

    //TODO Retourner uniquement les username et id et pas les mdp
    @Operation(summary = "Get User by ID")
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable UUID id) throws UserNotFoundByIdException {
        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
    }

    //TODO Retourner uniquement les username et id et pas les mdp
    @Operation(summary = "Get User by Username")
    @GetMapping("/username/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) throws UserNotFoundByNameException {
        return new ResponseEntity<>(userService.getUserByUsername(username), HttpStatus.OK);
    }

    @Operation(summary = "Update Username")
    @PutMapping("/{id}/username")
    public ResponseEntity<User> updateUsername(@PathVariable UUID id, @RequestParam String newUsername) throws UserNotFoundByIdException {
        return new ResponseEntity<>(userService.updateUsername(id, newUsername), HttpStatus.OK);
    }

    @Operation(summary = "Update Password")
    @PutMapping("/{id}/password")
    public ResponseEntity<User> updatePassword(@PathVariable UUID id, @RequestParam String newPassword) throws UserNotFoundByIdException {
        return new ResponseEntity<>(userService.updatePassword(id, newPassword), HttpStatus.OK);
    }

    @Operation(summary = "Delete User by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUserById(@PathVariable UUID id) throws UserNotFoundByIdException {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
