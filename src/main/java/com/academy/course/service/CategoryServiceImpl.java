package com.academy.course.service;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.categoryDao.CategoryDAOImpl;
import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.CategoryMapper;
import com.academy.course.mapper.MapperFactory;
import com.academy.course.mapper.ProductMapper;
import com.academy.course.model.Category;
import com.academy.course.model.Product;

import java.sql.SQLException;

public class CategoryServiceImpl implements CategoryService{
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final ProductMapper productMapper = MapperFactory.getProductMapper();
    private final CategoryMapper categoryMapper = MapperFactory.getCategoryMapper();

    @Override
    public void createCategory(CategoryDTO categoryDTO) throws SQLException {
        Category category = Category.builder()
                .name(categoryDTO.getName())
                .build();
        categoryDAO.save(category);
    }

    @Override
    public void addProductToCategory(Integer categoryId, ProductDTO productDTO) throws SQLException {
        Category category = categoryDAO.get(categoryId);
        Product product = productDAO.get(productDTO.getId());

        category.addProduct(product);

        categoryDAO.update(category);

    }

    @Override
    public void updateCategory(Integer oldValueId, CategoryDTO newValue) throws SQLException {
        Category category = categoryDAO.get(oldValueId);
        category.setProducts(category.getProducts());
        category.setName(newValue.getName());
        categoryDAO.update(category);
    }

    @Override
    public void deleteCategory(CategoryDTO categoryDTO) throws SQLException {
        Category category = categoryDAO.get(categoryDTO.getId());
        for (Product product : category.getProducts()){
            category.removeProduct(product);
        }
        categoryDAO.delete(category);

    }

    @Override
    public CategoryDTO getCategory(Integer id) throws SQLException {
        Category category = categoryDAO.get(id);
        return categoryMapper.mapToDTO(category);
    }
}
