package com.academy.course.exception;

public class LoginValidationException extends ValidationException{
    public LoginValidationException(String message) {
        super("Login validation error " + message);
    }
}
