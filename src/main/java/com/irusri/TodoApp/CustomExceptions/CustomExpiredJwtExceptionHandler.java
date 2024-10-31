package com.irusri.TodoApp.CustomExceptions;

import com.irusri.TodoApp.service.ToDoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class CustomExpiredJwtExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ToDoService.class);

    @ExceptionHandler(CustomExpiredJwtException.class)
    public ResponseEntity<ApiError> handleRuntimeException(CustomExpiredJwtException ex) {
        ApiError error = new ApiError(401, "Authentication token expired!", new Date());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
    }
}