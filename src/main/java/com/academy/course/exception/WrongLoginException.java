package com.academy.course.exception;

public class WrongLoginException extends BusinessException {
    public WrongLoginException(String message) {
        super(message);
    }
}
