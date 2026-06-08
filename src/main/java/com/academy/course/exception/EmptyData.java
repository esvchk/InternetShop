package com.academy.course.exception;

public class EmptyData extends RuntimeException {
    public EmptyData(String message) {
        super(message);
    }
}
