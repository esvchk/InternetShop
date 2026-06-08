package com.academy.course.service;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.categoryDao.CategoryDAOImpl;
import com.academy.course.dto.CategoryDTO;
import com.academy.course.mapper.MapperFactory;
import com.academy.course.mapper.ProductMapper;
import com.academy.course.model.Category;
import com.academy.course.model.Product;

import java.sql.SQLException;

public class CategoryServiceImpl implements CategoryService{
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();
    private final ProductMapper productMapper = MapperFactory.getProductMapper();

    @Override
    public void addCategory(CategoryDTO categoryDTO) throws SQLException {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        Product product = Product.builder()
                .category(category)
                .build();

        category.addProduct(product);
        categoryDAO.save(category);
    }

    @Override
    public void updateCategory(Integer oldValueId, CategoryDTO newValue) {

    }

    @Override
    public void deleteCategory(CategoryDTO categoryDTO) {

    }

    @Override
    public CategoryDTO getCategory(Integer id) {
        return null;
    }
}
