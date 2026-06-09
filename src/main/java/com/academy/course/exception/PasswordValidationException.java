package com.academy.course.exception;

public class PasswordValidationException extends ValidationException {
    public PasswordValidationException(String message) {
        super("Password validation error " + message);
    }
}
