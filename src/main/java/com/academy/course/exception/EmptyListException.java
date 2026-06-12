package com.academy.course.exception;

public class EmptyListException extends RuntimeException {
    public EmptyListException(String entityName) {
        super("Empty List of entities " + entityName);
        this.entityName = entityName;
    }
    private final String entityName;
}
