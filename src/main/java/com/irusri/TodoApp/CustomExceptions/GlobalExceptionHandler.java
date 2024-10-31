package com.irusri.TodoApp.CustomExceptions;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleRuntimeException(Exception ex){
        ApiError error = new ApiError(500, ex.getMessage(), new Date());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ApiError> handleExpiredJwtException(ExpiredJwtException ex) {
        String errorMessage = "Invalid JWT signature. Please provide a valid token.";

        ApiError error = new ApiError(401, "Authentication token expired!", new Date());

        // Return a meaningful response
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}