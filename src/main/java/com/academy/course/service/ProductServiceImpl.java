package com.academy.course.service;


import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.ProductMapper;

import com.academy.course.model.Product;
import com.academy.course.service.validator.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();
    private final IdValidatorFactory idValidatorFactory;
    private final BaseProductValidator baseProductValidator = new BaseProductValidatorImpl();
    private final BusinessProductValidator businessProductValidator = new BusinessProductValidatorImpl(baseProductValidator,productDAO);
    private final static Logger logger = LogManager.getLogger(ProductServiceImpl.class);


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
        logger.info("Product limit has been successfully installed");
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
            logger.info("Product with id{} has been successfully updated",oldValueId);
    }

    @Override
    public void addProduct(ProductDTO productDTO) throws SQLException {
        baseProductValidator.validateCreationProduct(productDTO);
        productDAO.save(productMapper.mapToEntity(productDTO));
        logger.info("New product {} has been successfully added",productDTO);
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) throws SQLException {
        idValidatorFactory.getProductValidator().validateId(productDTO.getId());
        productDAO.delete(productDAO.get(productDTO.getId()));
        logger.info("Product {} has been successfully deleted",productDTO);
    }

    @Override
    public ProductDTO getProductById(Integer id) throws SQLException {
        idValidatorFactory.getProductValidator().validateId(id);
        logger.info("Product with id {} was successfully found",id);
        return productMapper.mapToDTO(productDAO.get(id));
    }

    @Override
    public ProductDTO findProductsByName(String name) {
        businessProductValidator.validateFindProductByName(name);
        logger.info("Product with name {} was successfully found", name);
        return productMapper.mapToDTO(productDAO.getByName(name));
    }

    @Override
    public Set<ProductDTO> getAllProducts() {
        businessProductValidator.validateGetAllProducts();
        logger.info("Get all products successful");
        return productMapper.mapToSetDTOS(productDAO.getAllProducts());
    }

    @Override
    public Set<ProductDTO> getAllProductsFromCategory(CategoryDTO categoryDTO) throws SQLException {
        baseProductValidator.validateGetAllProductsFromCategory(categoryDTO);
        logger.info("Search products by category {} successful",categoryDTO.getName());
        return categoryDTO.getProductsDTO();
    }


}
