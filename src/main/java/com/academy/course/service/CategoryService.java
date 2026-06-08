package com.academy.course.service;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.model.Category;

import java.sql.SQLException;

public interface CategoryService {
    void addCategory(CategoryDTO categoryDTO) throws SQLException;
    void updateCategory(Integer oldValueId,CategoryDTO newValue);
    void deleteCategory(CategoryDTO categoryDTO);
    CategoryDTO getCategory(Integer id);
}
