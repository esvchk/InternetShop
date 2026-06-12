package com.academy.course.exception;

import lombok.Getter;

@Getter
public class ProductAddingException extends RuntimeException {

    public ProductAddingException(String message) {
        super(message);
    }

}
