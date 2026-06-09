package com.academy.course.exception;

import lombok.Getter;

@Getter
public class EmptyEntityException extends ValidationException {

    public EmptyEntityException(Object entity) {
        super("Entity " + entity + "cannot be empty");
        this.entity = entity;
    }


    private final Object entity;


}
