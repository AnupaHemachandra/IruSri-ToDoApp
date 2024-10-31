package com.irusri.TodoApp.CustomExceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@ControllerAdvice
public class NoSuchCustomerExistsExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchCustomerExistsException.class)
    public ResponseEntity<ApiError> handleRuntimeException(NoSuchCustomerExistsException ex) {
        ApiError error = new ApiError(400, "User does not exist!", new Date());
        logger.error(ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}