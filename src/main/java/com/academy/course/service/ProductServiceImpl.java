package com.academy.course.service;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.categoryDao.CategoryDAOImpl;
import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.ProductMapper;
import com.academy.course.model.Category;
import com.academy.course.model.Product;
import com.academy.course.service.validator.*;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();
    private final IdValidatorFactory idValidatorFactory;
    private final BaseProductValidator baseProductValidator = new BaseProductValidatorImpl();
    private final BusinessProductValidator businessProductValidator = new BusinessProductValidatorImpl(baseProductValidator,productDAO);


    public ProductServiceImpl(IdValidatorFactory idValidatorFactory) {
        this.idValidatorFactory = idValidatorFactory;

    }

    @Override
    public void setProductLimit(ProductDTO productDTO, Integer limit) throws SQLException {
        idValidatorFactory.getProductValidator().validateId(productDTO.getId());
        baseProductValidator.validateSetProductLimit(limit);
        Product product = productDAO.get(productDTO.getId());
        if (limit == null) {
            product.setProductLimit(null);
        } else {
            product.setProductLimit(limit);
            product.setIsAvailable(!product.getProductLimit().equals(0));
        }
        productDAO.update(product);
    }

    @Override
    public void updateProduct(Integer oldValueId, ProductDTO newValue) throws SQLException {
        idValidatorFactory.getProductValidator().validateId(oldValueId);
        baseProductValidator.validateUpdatingProduct(newValue);
        Product product = productDAO.get(oldValueId);
            product.setInfo(newValue.getInfo());
            product.setName(newValue.getName());
            product.setPrice(newValue.getPrice());
            product.setIsAvailable(newValue.getIsAvailable());
            productDAO.update(product);
    }

    @Override
    public void addProduct(ProductDTO productDTO) throws SQLException {
        productDAO.save(productMapper.mapToEntity(productDTO));
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) throws SQLException {
        Product product = productDAO.get(productDTO.getId());
        productDAO.delete(product);
    }

    @Override
    public ProductDTO getProductById(Serializable id) throws SQLException {
        return productMapper.mapToDTO(productDAO.get(id));
    }

    @Override
    public ProductDTO findProductsByName(String name) {
        return productMapper.mapToDTO(productDAO.getByName(name));
    }

    @Override
    public Set<ProductDTO> getAllProducts() {
        return productMapper.mapToSetDTOS(productDAO.getAllProducts());
    }

    @Override
    public Set<ProductDTO> getAllProductsFromCategory(CategoryDTO categoryDTO) throws SQLException {
        Category category = categoryDAO.get(categoryDTO.getId());
        return productMapper.mapToSetDTOS(category.getProducts());
    }


}
