package com.academy.course.service.validator;

import com.academy.course.exception.EntityNotFoundByIdException;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class IdValidationService implements IdValidator {

    private final EntityFetcher entityFetcher;
    private final String entityName;
    private static final Logger logger = LogManager.getLogger(IdValidationService.class);


    public IdValidationService(EntityFetcher entityFetcher, String entityName) {
        this.entityFetcher = entityFetcher;
        this.entityName = entityName;
    }

    @Override
    public boolean validateId(Integer entityId) {
        if (entityId <= 0) {
            logger.warn("Wrong input id {}",entityId);
            throw new InvalidInputException(String.valueOf(entityId));
        }
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
        return true;
    }

}
