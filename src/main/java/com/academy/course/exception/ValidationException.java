package com.academy.course.exception;

public abstract class ValidationException extends BusinessException {
    public ValidationException(String message) {
        super(message);
    }

}
