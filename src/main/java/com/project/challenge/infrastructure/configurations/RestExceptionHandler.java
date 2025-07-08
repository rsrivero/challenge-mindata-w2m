package com.project.challenge.infrastructure.configurations;

import com.project.challenge.application.exceptions.SpaceshipNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SpaceshipNotFound.class)
    protected ResponseEntity<Map<String, Object>> handleSpaceshipNotFound(SpaceshipNotFound ex, WebRequest request) {
        log.warn("Spaceship not found: {}", ex.getMessage());
        return buildResponse(HttpStatus.NOT_FOUND, "Spaceship not found", "The requested spaceship does not exist");
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        log.warn("Validation failed: {}", errors);
        return new ResponseEntity<>(Map.of(
                "message", "Validation failed",
                "errors", errors,
                "status", status.value()
        ), headers, status);
    }

    private ResponseEntity<Map<String, Object>> buildResponse(HttpStatusCode status, String message, String error) {
        return ResponseEntity.status(status).body(Map.of(
                "message", message,
                "error", error,
                "status", status.value()
        ));
    }
}
