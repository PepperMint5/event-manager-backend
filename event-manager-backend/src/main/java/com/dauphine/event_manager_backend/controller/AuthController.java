package com.dauphine.event_manager_backend.controller;

import com.dauphine.event_manager_backend.dto.AuthRequest;
import com.dauphine.event_manager_backend.exceptions.UserNameAlreadyExistsException;
import com.dauphine.event_manager_backend.exceptions.UserNotFoundByNameException;
import com.dauphine.event_manager_backend.model.User;
import com.dauphine.event_manager_backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(
        name = "Authentification API",
        description = "Endpoints for authentification"
)
@RequestMapping("/v1/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }


    @PostMapping("/login")
    @Operation(
            summary = "Login user",
            description = "Logs in a user with username and password"
    )
    public ResponseEntity<String> login(@RequestBody AuthRequest authRequest) throws UserNotFoundByNameException {
        User user = authService.login(authRequest.getUsername(), authRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok().body("{\"userId\":\"" + user.getId() + "\"}");
        }
        return ResponseEntity.status(401).body("Invalid credentials");
    }

    @PostMapping("/register")
    @Operation(
            summary = "Register user",
            description = "Registers a new user with username and password"
    )
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) throws UserNameAlreadyExistsException {
        User user = authService.registerUser(authRequest.getUsername(), authRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok().body("{\"userId\":\"" + user.getId() + "\"}");
        }
        return ResponseEntity.status(401).body("Internal error");
    }

}
