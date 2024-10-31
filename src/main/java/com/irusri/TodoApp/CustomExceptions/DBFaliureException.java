package com.irusri.TodoApp.CustomExceptions;

public class DBFaliureException extends RuntimeException{
    public DBFaliureException() {
    }

    public DBFaliureException(String message) {
        super(message);
    }
}
