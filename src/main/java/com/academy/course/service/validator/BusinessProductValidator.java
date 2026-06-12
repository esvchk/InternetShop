package com.academy.course.service.validator;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;

public interface BusinessProductValidator {
    void validateSetProductLimit(ProductDTO productDTO,Integer limit);
    void validateUpdatingProduct(ProductDTO newValue);
    void validateCreationProduct(ProductDTO productDTO);
    void validateFindProductByName(String name);
    void validateGetAllProducts();
    void validateGetAllProductsFromCategory(CategoryDTO categoryDTO);
}
