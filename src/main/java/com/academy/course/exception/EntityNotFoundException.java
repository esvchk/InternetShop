package com.academy.course.exception;

public class EntityNotFoundException extends BusinessException {
    public EntityNotFoundException(String message, Integer entityId) {
        super(message);
        this.entityId = entityId;
    }

    public EntityNotFoundException(Integer entityId) {
        super("Entity with id " + entityId + " not found");
        this.entityId = entityId;
    }

    public EntityNotFoundException(Throwable cause, Integer entityId) {
        super("Entity with id " + entityId + " not found", cause);
        this.entityId = entityId;
    }

    private final Integer entityId;
}
