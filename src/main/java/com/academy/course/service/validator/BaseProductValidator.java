package com.academy.course.service.validator;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.util.Set;

public interface BaseProductValidator {
    void nameInputValidation(String name);
    void validateGetAllProductsFromCategory(CategoryDTO categoryDTO) throws SQLException;
    void validateNegativeNumber(Number number);
    void validateSetProductLimit(Integer limit);
    void validateUpdatingProduct(ProductDTO newValue);
    void validateCreationProduct(ProductDTO productDTO);
    void validatePagination(Integer offSet, Integer size, Long totalSize, Set<ProductDTO> setToPaginate);
}
