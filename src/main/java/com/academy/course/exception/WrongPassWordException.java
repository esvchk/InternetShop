package com.academy.course.exception;

public class WrongPassWordException extends BusinessException{
    public WrongPassWordException(String message) {
        super(message);
    }
}
