package com.academy.course.exception;

import lombok.Getter;

@Getter
public class DuplicateEmployeeException extends RuntimeException {

    public DuplicateEmployeeException(String login) {
        super("Duplicate unique result for Employee with login " + login);
        this.login = login;
    }

    private final String login;
}
