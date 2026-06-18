package com.academy.course.service;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.categoryDao.CategoryDAOImpl;
import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.CategoryMapper;
import com.academy.course.mapper.factory.MapperFactory;
import com.academy.course.model.Category;
import com.academy.course.model.Product;
import com.academy.course.service.validator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Set;

public class CategoryServiceImpl implements CategoryService {

    private static final Logger logger = LogManager.getLogger(CategoryServiceImpl.class);
    private final CategoryDAO categoryDAO;
    private final ProductDAO productDAO ;
    private final CategoryMapper categoryMapper ;
    private final BusinessCategoryValidator businessCategoryValidator;
    private final BaseCategoryValidator baseCategoryValidator;
    private final IdValidatorFactory idValidatorFactory;

    public CategoryServiceImpl(CategoryDAO categoryDAO,
                               ProductDAO productDAO, CategoryMapper categoryMapper,
                               BusinessCategoryValidator businessCategoryValidator,
                               BaseCategoryValidator baseCategoryValidator,
                               IdValidatorFactory idValidatorFactory) {
        this.categoryDAO = categoryDAO;
        this.productDAO = productDAO;
        this.categoryMapper = categoryMapper;
        this.businessCategoryValidator = businessCategoryValidator;
        this.baseCategoryValidator = baseCategoryValidator;
        this.idValidatorFactory = idValidatorFactory;
    }

    @Override
    public void createCategory(CategoryDTO categoryDTO) throws SQLException {
        idValidatorFactory.getCategoryValidator().validateId(categoryDTO.getId());
        baseCategoryValidator.createCategory(categoryDTO);
        categoryDAO.save(categoryMapper.mapToEntity(categoryDTO));
        logger.info("New category {} was successfully added",categoryDTO.getName());
    }

    @Override
    public void addProductToCategory(Integer categoryId, ProductDTO productDTO) throws SQLException {
        idValidatorFactory.getCategoryValidator().validateId(categoryId);
        idValidatorFactory.getProductValidator().validateId(productDTO.getId());
        Category category = categoryDAO.get(categoryId);
        Product product = productDAO.get(productDTO.getId());
        category.addProduct(product);
        categoryDAO.update(category);
        logger.info("New product {} was successfully added into category with id {}",
                productDTO.getName(),categoryId);
    }

    @Override
    public void updateCategory(Integer oldValueId, CategoryDTO newValue) throws SQLException {
        idValidatorFactory.getCategoryValidator().validateId(oldValueId);
        baseCategoryValidator.updateCategory(newValue);
        Category category = categoryDAO.get(oldValueId);
        category.setProducts(category.getProducts());
        category.setName(newValue.getName());
        categoryDAO.update(category);
        logger.info("Category with id {} was successfully updated",oldValueId);
    }

    @Override
    public void deleteCategory(CategoryDTO categoryDTO) throws SQLException {
        idValidatorFactory.getCategoryValidator().validateId(categoryDTO.getId());
        Category category = categoryDAO.get(categoryDTO.getId());
        for (Product product : category.getProducts()) {
            category.removeProduct(product);
        }
        categoryDAO.delete(category);
        logger.info("Category with name {} was successfully deleted",category.getName());
    }

    @Override
    public CategoryDTO getCategoryById(Integer id) throws SQLException {
        idValidatorFactory.getCategoryValidator().validateId(id);
        logger.info("Category with id {} was successfully found",id);
        return categoryMapper.mapToDTO(categoryDAO.get(id));
    }

    @Override
    public Set<CategoryDTO> getAllCategories() {
        businessCategoryValidator.getAllCategories();
        logger.info("Successful getting all categories");
        return categoryMapper.mapToSetDTOS(categoryDAO.getAllCategories());
    }
}
