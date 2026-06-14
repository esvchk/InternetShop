package com.academy.course.service.validator;

import com.academy.course.dto.CategoryDTO;

public interface BaseCategoryValidator {
    void createCategory(CategoryDTO categoryDTO) ;
    void updateCategory(CategoryDTO newValue) ;
    void namingValidator(String name);
}
