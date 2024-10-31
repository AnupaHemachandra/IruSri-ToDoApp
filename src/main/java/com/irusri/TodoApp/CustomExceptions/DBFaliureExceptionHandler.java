package com.irusri.TodoApp.CustomExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

@RestControllerAdvice
public class DBFaliureExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(DBFaliureException.class)
    public ResponseEntity<ApiError> handleRuntimeException(DBFaliureException ex){
        ApiError error =  new ApiError(500, "An unknown error occurred from the Database! Please contact admin!", new Date());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}