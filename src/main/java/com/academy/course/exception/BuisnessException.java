package com.academy.course.exception;

public abstract class BuisnessException extends RuntimeException {
    public BuisnessException(String message) {
        super(message);
    }
}
