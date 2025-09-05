package com.example.oca42.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<Map<String, Object>> handle(NotFoundException exception, WebRequest request) {
        return ofType(request, HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @ExceptionHandler({AlreadyExistException.class})
    public ResponseEntity<Map<String, Object>> handle(AlreadyExistException exception, WebRequest request) {
        return ofType(request, HttpStatus.CONFLICT, exception.getMessage());
    }

    @ExceptionHandler({Throwable.class})
    public ResponseEntity<Map<String, Object>> handleRuntimeException(Throwable exception, WebRequest request) {
        return ofType(request, HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message) {
        return ofType(request, status, message, null);
    }

    private ResponseEntity<Map<String, Object>> ofType(WebRequest request, HttpStatus status, String message, List<ConstraintsViolationError> validationErrors) {
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("STATUS", status.value());
        attributes.put("ERROR", status.getReasonPhrase());
        attributes.put("MESSAGE", message);
        attributes.put("ERRORS", validationErrors);
        attributes.put("PATH", ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(attributes, status);
    }

}