package com.academy.course.service.validator;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.EmptyListException;
import com.academy.course.exception.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

;
import java.util.Set;

public class BusinessCategoryValidatorImpl implements BusinessCategoryValidator{

    private static final Logger logger = LogManager.getLogger(BusinessCategoryValidatorImpl.class);

    private final CategoryDAO categoryDAO;

    public BusinessCategoryValidatorImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }


    @Override
    public void getAllCategories() {
        if (categoryDAO.getAllCategories() == null||categoryDAO.getAllCategories().isEmpty()) {
            logger.warn("Try to get empty list of categories");
            throw new EmptyListException("Category");
        }
    }


}
