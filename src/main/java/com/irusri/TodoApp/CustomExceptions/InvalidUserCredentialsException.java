package com.irusri.TodoApp.CustomExceptions;

public class InvalidUserCredentialsException extends RuntimeException {
    public InvalidUserCredentialsException() {
    }

    public InvalidUserCredentialsException(String message) {
        super(message);
    }
}
