package com.academy.course.exception;

public class WrongPassWord extends RuntimeException {
    public WrongPassWord(String message) {
        super(message);
    }
}
