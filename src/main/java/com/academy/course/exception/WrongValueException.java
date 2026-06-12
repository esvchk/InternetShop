package com.academy.course.exception;

public class WrongValueException extends ValidationException {
    public WrongValueException(String message, String value) {
        super(message);
        this.value = value;
    }

    private final String value;
}
