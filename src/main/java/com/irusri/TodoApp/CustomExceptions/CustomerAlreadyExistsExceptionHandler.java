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
public class CustomerAlreadyExistsExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ToDoService.class);

    @ExceptionHandler(CustomerAlreadyExistsException.class)
    public ResponseEntity<ApiError> handleRuntimeException(CustomerAlreadyExistsException ex) {
        ApiError error = new ApiError(400, "A customer with given username already exists!", new Date());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}