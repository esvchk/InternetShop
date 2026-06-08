package com.academy.course.service;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Category;

import java.sql.SQLException;
import java.util.Set;

public interface CategoryService {
    void createCategory(CategoryDTO categoryDTO) throws SQLException;
    void addProductToCategory(Integer categoryId, ProductDTO productDTO) throws SQLException;
    void updateCategory(Integer oldValueId,CategoryDTO newValue) throws SQLException;
    void deleteCategory(CategoryDTO categoryDTO) throws SQLException;
    CategoryDTO getCategory(Integer id) throws SQLException;
    Set<CategoryDTO> getAllCategories();
}
