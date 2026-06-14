package com.academy.course.service.validator;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;

import java.io.Serializable;
import java.sql.SQLException;

public interface BusinessProductValidator {

    void validateFindProductByName(String name);
    void validateGetAllProducts();

}
