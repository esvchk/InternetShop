package com.academy.course.service.validator;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;

public class BusinessProductValidatorImpl implements BusinessProductValidator,EmptyFieldValidator<String> {
    @Override
    public void validateSetProductLimit(ProductDTO productDTO, Integer limit) {

    }

    @Override
    public void validateUpdatingProduct(ProductDTO newValue) {

    }

    @Override
    public void validateCreationProduct(ProductDTO productDTO) {

    }

    @Override
    public void validateFindProductByName(String name) {

    }

    @Override
    public void validateGetAllProducts() {

    }

    @Override
    public void validateGetAllProductsFromCategory(CategoryDTO categoryDTO) {

    }

    @Override
    public void validateField(String string) {

    }
}
