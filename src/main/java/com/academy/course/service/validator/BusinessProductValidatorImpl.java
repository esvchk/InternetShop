package com.academy.course.service.validator;

import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.EmptyListException;
import com.academy.course.exception.EntityNotFoundByNameException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class BusinessProductValidatorImpl implements BusinessProductValidator, EmptyFieldValidator<String> {

    private final static Logger logger = LogManager.getLogger(BusinessProductValidatorImpl.class);
    private final BaseProductValidator baseProductValidator;
    private final ProductDAO productDAO;
    public BusinessProductValidatorImpl(BaseProductValidator baseProductValidator, ProductDAO productDAO) {
        this.baseProductValidator = baseProductValidator;
        this.productDAO = productDAO;

    }


    @Override
    public void validateFindProductByName(String name) {
        validateField(name);
        baseProductValidator.nameInputValidation(name);
        if (productDAO.getByName(name) == null || productDAO.getByName(name).isEmpty()) {
            logger.warn("Search by name {} failed",name);
            throw new EntityNotFoundByNameException(name);
        }
    }

    @Override
    public void validateGetAllProducts() {
        if (productDAO.getAllProducts() == null || productDAO.getAllProducts().isEmpty()) {
            logger.warn("Try to get empty list of categories");
            throw new EmptyListException("Product");
        }
    }


    @Override
    public void validateField(String fieldName) {
        if (fieldName == null || fieldName.trim().isEmpty()) {
            logger.warn("Try to input empty field{}",fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
