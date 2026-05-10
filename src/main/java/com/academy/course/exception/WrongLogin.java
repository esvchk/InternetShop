package com.academy.course.exception;

public class WrongLogin extends RuntimeException {
    public WrongLogin(String message) {
        super(message);
    }
}
