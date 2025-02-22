package com.irusri.TodoApp.CustomExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class NoItemsFoundExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoItemsFoundException.class)
    public ResponseEntity<ApiError> handleRuntimeException(NoItemsFoundException ex){
        ApiError error = new ApiError(404, "Items not found!", new Date());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatusCode.valueOf(404));
    }
}