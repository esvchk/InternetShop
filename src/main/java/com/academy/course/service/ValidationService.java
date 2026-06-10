package com.academy.course.service;

import com.academy.course.exception.EntityNotFoundByIdException;
import com.academy.course.exception.EmptyFieldException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class ValidationService implements Validator {

    private final EntityFetcher entityFetcher;
    private final String entityName;
    private static final Logger logger = LogManager.getLogger(ValidationService.class);


    public ValidationService(EntityFetcher entityFetcher, String entityName) {
        this.entityFetcher = entityFetcher;
        this.entityName = entityName;
    }

    @Override
    public void validateId(Integer entityId) {
        if (entityId == null) {
            logger.warn("Empty entity with id {}",entityName);
            throw new EmptyFieldException("entityId");
        }
        try {
            if (!entityFetcher.existsById(entityId)) {
                logger.warn("Entity{} with {} id not found",entityName,entityId);
                throw new EntityNotFoundByIdException(entityId);
            }
        } catch (SQLException e){
            logger.warn("DataBase error",e);
        }

    }

}
