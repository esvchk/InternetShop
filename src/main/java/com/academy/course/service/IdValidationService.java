package com.academy.course.service;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.categoryDao.CategoryDAOImpl;
import com.academy.course.dao.employeeDao.EmployeeDAO;
import com.academy.course.dao.employeeDao.EmployeeDAOImpl;
import com.academy.course.dao.itemDao.ItemDAO;
import com.academy.course.dao.itemDao.ItemDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.exception.EntityNotFoundException;
import com.academy.course.exception.EmptyFieldException;
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
    public void validate(Integer entityId) {
        if (entityId == null) {
            logger.warn("Empty entity with id {}",entityName);
            throw new EmptyFieldException("entityId");
        }
        try {
            if (!entityFetcher.exists(entityId)) {
                logger.warn("Entity{} with {} id not found",entityName,entityId);
                throw new EntityNotFoundException(entityId);
            }
        } catch (SQLException e){
            logger.warn("DataBase error",e);
        }

    }
}
