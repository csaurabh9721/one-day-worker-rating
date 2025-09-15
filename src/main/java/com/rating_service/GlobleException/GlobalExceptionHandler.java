package com.rating_service.GlobleException;

import com.rating_service.DTO.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<Map<String, String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        APIResponse<Map<String, String>> response = new APIResponse<>(HttpStatus.BAD_REQUEST.value(), errors, "Validation failed");

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<APIResponse<String>> handleResourceNotFound(ResourceNotFoundException ex) {
        APIResponse<String> response = new APIResponse<>(HttpStatus.NOT_FOUND.value(), null, ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<APIResponse<String>> handleGenericException(Exception ex) {
        APIResponse<String> response = new APIResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), null, "Something went wrong! Please try again later.");


        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
