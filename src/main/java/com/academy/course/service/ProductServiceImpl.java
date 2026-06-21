package com.academy.course.service;


import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.ProductMapper;
import com.academy.course.model.Product;
import com.academy.course.paginator.PaginatedResult;
import com.academy.course.service.validator.BaseProductValidator;
import com.academy.course.service.validator.BusinessProductValidator;
import com.academy.course.service.validator.IdValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;

public class ProductServiceImpl extends PaginatedResult<ProductDTO> implements ProductService {

    private final ProductDAO productDAO;
    private final ProductMapper productMapper;
    private final IdValidatorFactory idValidatorFactory;
    private final BaseProductValidator baseProductValidator;
    private final BusinessProductValidator businessProductValidator;
    private final static Logger logger = LogManager.getLogger(ProductServiceImpl.class);


    public ProductServiceImpl(ProductDAO productDAO, ProductMapper productMapper, IdValidatorFactory idValidatorFactory, BaseProductValidator baseProductValidator, BusinessProductValidator businessProductValidator) {
        super(ProductDTO.class);
        this.productDAO = productDAO;
        this.productMapper = productMapper;
        this.idValidatorFactory = idValidatorFactory;
        this.baseProductValidator = baseProductValidator;
        this.businessProductValidator = businessProductValidator;
    }

    @Override
    public PaginatedResult<ProductDTO> getPaginatedListOfProducts(int offset, int size) {
        Long totalSize = productDAO.countProducts();
        Set<ProductDTO> products =
                productMapper.mapToSetDTOS(productDAO.getAllProducts((offset - 1) * size, size));
        baseProductValidator.validatePagination(offset,size,totalSize,products);
        logger.info("Successful getting pages of products");
        return paginate(offset,size,totalSize,products);
    }


    @Override
    public void setProductLimit(ProductDTO productDTO, Integer limit) throws SQLException {
        idValidatorFactory.getProductValidator().validateId(productDTO.getId());
        Product product = productDAO.get(productDTO.getId());
        if (limit == null) {
            product.setProductLimit(null);
        } else {
            baseProductValidator.validateSetProductLimit(limit);
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
        setProductLimit(productMapper.mapToDTO(product), newValue.getProductLimit());
        productDAO.update(product);
        logger.info("Product with id {} has been successfully updated", oldValueId);
    }

    @Override
    public void addProduct(ProductDTO productDTO) throws SQLException {
        baseProductValidator.validateCreationProduct(productDTO);
        productDAO.save(productMapper.mapToEntity(productDTO));
        logger.info("New product {} has been successfully added", productDTO);
    }

    @Override
    public void deleteProduct(Integer id) throws SQLException {
        idValidatorFactory.getProductValidator().validateId(id);
        productDAO.delete(productDAO.get(id));
        logger.info("Product {} has been successfully deleted", id);
    }

    @Override
    public ProductDTO getProductById(Integer id) throws SQLException {
        idValidatorFactory.getProductValidator().validateId(id);
        logger.info("Product with id {} was successfully found", id);
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
        logger.info("Search products by category {} successful", categoryDTO.getName());
        return categoryDTO.getProductsDTO();
    }

}
