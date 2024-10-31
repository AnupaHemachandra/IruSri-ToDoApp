package com.irusri.TodoApp.CustomExceptions;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.Date;

@RestControllerAdvice   //This method will only work if our request is being handled by DispatcherServlet.
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleRuntimeException(Exception ex){
        ApiError error = null;
        logger.error(ex.getMessage());
        if(ex instanceof BadCredentialsException){
            error = new ApiError(401, ex.getMessage(), new Date());
            return new ResponseEntity<>(error, HttpStatusCode.valueOf(401));
        }
        if(ex instanceof AccessDeniedException){
            error = new ApiError(403, ex.getMessage(), new Date());
            return new ResponseEntity<>(error, HttpStatusCode.valueOf(403));
        }
        //These are handled before the Dispatcher by the Authorization filter or Spring security
        //specific filter
        if(ex instanceof ExpiredJwtException){
            error = new ApiError(403, "Authentication token expired!", new Date());
            return new ResponseEntity<>(error, HttpStatusCode.valueOf(403));
        }
        if(ex instanceof SignatureException){
            error = new ApiError(403, "Invalid JWT signature! Check the JWT token or authenticate again!", new Date());
            return new ResponseEntity<>(error, HttpStatusCode.valueOf(403));
        }

        return new ResponseEntity<>(error, HttpStatusCode.valueOf(500));
    }
}