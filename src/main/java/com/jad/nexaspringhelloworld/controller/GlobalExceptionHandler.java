package com.jad.nexaspringhelloworld.controller;

import com.jad.nexaspringhelloworld.service.RessourceNotFoundException;
import com.jad.nexaspringhelloworld.service.ServiceOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(RessourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(RessourceNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(this.errorBody(exception.getMessage(), HttpStatus.NOT_FOUND.value()));
    }

    private Map<String, Object> errorBody(final String message, final int status) {
        return Map.of(
                "timestamp", Instant.now().toString(),
                "status", status,
                "message", message
                     );
    }

    @ExceptionHandler(ServiceOperationException.class)
    public ResponseEntity<Map<String, Object>> handleServiceError(ServiceOperationException exception) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_CONTENT)
                .body(this.errorBody(exception.getMessage(), HttpStatus.UNPROCESSABLE_CONTENT.value()));
    }
}
