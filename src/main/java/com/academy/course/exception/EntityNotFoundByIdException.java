package com.academy.course.exception;

public class EntityNotFoundByIdException extends BusinessException {
    public EntityNotFoundByIdException(String message, Integer entityId) {
        super(message);
        this.entityId = entityId;
    }

    public EntityNotFoundByIdException(Integer entityId) {
        super("Entity with id " + entityId + " not found");
        this.entityId = entityId;
    }

    public EntityNotFoundByIdException(Throwable cause, Integer entityId) {
        super("Entity with id " + entityId + " not found", cause);
        this.entityId = entityId;
    }

    private final Integer entityId;

}
