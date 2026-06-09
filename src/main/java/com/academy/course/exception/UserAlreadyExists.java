package com.academy.course.exception;

public class UserAlreadyExists extends BuisnessException{
    public UserAlreadyExists(String message) {
        super(message);
    }

}
