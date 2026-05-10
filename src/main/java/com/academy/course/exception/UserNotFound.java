package com.academy.course.exception;

public class UserNotFound extends Exception {
    public UserNotFound(String message, Throwable cause) {
        super(message);
    }

    public UserNotFound(String message) {
        super(message);
    }
}
