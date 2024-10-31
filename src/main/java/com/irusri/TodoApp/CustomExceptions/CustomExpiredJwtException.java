package com.irusri.TodoApp.CustomExceptions;

public class CustomExpiredJwtException extends RuntimeException{
    public CustomExpiredJwtException() {
    }

    public CustomExpiredJwtException(String message) {
        super(message);
    }
}
