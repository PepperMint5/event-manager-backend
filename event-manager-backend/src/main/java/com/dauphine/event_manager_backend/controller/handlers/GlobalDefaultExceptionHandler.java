package com.dauphine.event_manager_backend.controller.handlers;

import com.dauphine.event_manager_backend.exceptions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    @ExceptionHandler({
            CategoryNotFoundByIdException.class,
            EventNotFoundByIdException.class,
            UserNotFoundByIdException.class,
            UserNotFoundByNameException.class
    })
    public ResponseEntity<String> handleNotFoundException(Exception ex) {
        logger.warn("[NOT FOUND] {}", ex.getMessage());
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }

    @ExceptionHandler({
            EventNameAlreadyExistsException.class,
            UserNameAlreadyExistsException.class,
            LoginIncorrectException.class
    })
    public ResponseEntity<String> handleBadRequestException(Exception ex) {
        logger.warn("[BAD REQUEST] {}", ex.getMessage());
        return ResponseEntity
                .status(400)
                .body(ex.getMessage());
    }
}
