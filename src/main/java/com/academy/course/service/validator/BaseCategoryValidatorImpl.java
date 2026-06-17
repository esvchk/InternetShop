package com.academy.course.service.validator;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseCategoryValidatorImpl implements BaseCategoryValidator,EmptyFieldValidator<String>{

    private static final Logger logger = LogManager.getLogger(BaseCategoryValidatorImpl.class);


    @Override
    public void createCategory(CategoryDTO categoryDTO){
        validateField(categoryDTO.getName());
        namingValidator(categoryDTO.getName());
    }

    @Override
    public void updateCategory(CategoryDTO newValue) {
        validateField(newValue.getName());
        namingValidator(newValue.getName());
    }

    @Override
    public void namingValidator(String name) {
        String uppercaseLetter = "^([A-Z])([a-z]\\s,-*)";
        if (!name.matches(uppercaseLetter)) {
            logger.warn("Naming category failed");
            throw new InvalidInputException("Category name must begin from uppercase letter",name);
        }
    }

    @Override
    public void validateField(String fieldName) {
        if (fieldName.equals("null")||fieldName.trim().isEmpty()) {
            logger.warn("Empty field{}",fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
