package com.academy.course.exception;

import lombok.Getter;

@Getter
public class EntityNotFoundByLoginException extends RuntimeException {


    public EntityNotFoundByLoginException(String login) {
        super("Entity with login " + login + " not found");
        this.login = login;
    }

    private final String login;
}
