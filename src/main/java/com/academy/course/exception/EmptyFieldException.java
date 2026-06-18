package com.academy.course.exception;

import lombok.Getter;

@Getter
public class EmptyFieldException extends ValidationException {
    private final String fieldName;

    public EmptyFieldException(String fieldName) {
        super("Field " + fieldName + " cannot be empty");
        this.fieldName = fieldName;
    }

    public EmptyFieldException(String message, String fieldName) {
        super(message);
        this.fieldName = fieldName;
    }
}
