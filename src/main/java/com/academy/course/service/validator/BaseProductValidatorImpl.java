package com.academy.course.service.validator;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.EmptyListException;
import com.academy.course.exception.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BaseProductValidatorImpl implements BaseProductValidator,EmptyFieldValidator<String>{


    private static final Logger logger = LogManager.getLogger(BaseProductValidator.class);



    @Override
    public void nameInputValidation(String name) {
        validateField(name);
        List<String> errors = new ArrayList<>();
        String figures = "(.*[0-9].*)";
        if (name.matches(figures)) {
            logger.warn("Try to input figure in product name {}",name);
            errors.add("Name of a product cannot contain figures");
        }
        String upperCase = "(.*[A-Z{1}].*)";
        if (name.matches(upperCase)) {
            logger.warn("Naming without uppercase letter{}",name);
            errors.add("Name of a product must contain uppercase letter");
        }
        if (!errors.isEmpty()) {
            String message = String.join(",",errors);
            throw new InvalidInputException(message,name);
        }
    }

    @Override
    public void validateGetAllProductsFromCategory(CategoryDTO categoryDTO){
        if (categoryDTO.getProductsDTO() == null || categoryDTO.getProductsDTO().isEmpty()) {
            logger.warn("Search by category {} failed",categoryDTO.getName());
            throw new EmptyListException("Product");
        }
    }

    @Override
    public void validateNegativeNumber(Number number) {
        if (number instanceof Integer && number.intValue() < 0) {
            throw new InvalidInputException("Negative number", number.toString());
        } else if (number instanceof Double && number.doubleValue() < 0) {
            throw new InvalidInputException("Negative number", number.toString());
        }

    }

    @Override
    public void validateSetProductLimit(Integer limit) {
        validateNegativeNumber(limit);
    }

    @Override
    public void validateUpdatingProduct(ProductDTO newValue) {
        validateField(newValue.getName());
        validateField(String.valueOf(newValue.getPrice()));
        validateNegativeNumber(newValue.getPrice());
    }

    @Override
    public void validateCreationProduct(ProductDTO productDTO) {
        validateField(productDTO.getName());
        validateField(String.valueOf(productDTO.getPrice()));
        validateNegativeNumber(productDTO.getPrice());
    }

    @Override
    public void validateField(String fieldName) {
        if (fieldName == null||fieldName.trim().isEmpty()) {
            logger.warn("Try to input empty value in field{}",fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
