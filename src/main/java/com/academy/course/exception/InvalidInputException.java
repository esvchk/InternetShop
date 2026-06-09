package com.academy.course.exception;


import lombok.Getter;

@Getter
public class InvalidInputException extends ValidationException {

    private final String input;

    public InvalidInputException(String input) {
        super("Wrong input " + input);
        this.input = input;
    }
}
