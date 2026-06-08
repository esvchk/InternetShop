package com.academy.course.service;

import com.academy.course.dao.categoryDao.CategoryDAO;
import com.academy.course.dao.categoryDao.CategoryDAOImpl;
import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.CategoryMapper;
import com.academy.course.mapper.MapperFactory;
import com.academy.course.mapper.ProductMapper;
import com.academy.course.model.Category;
import com.academy.course.model.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    @Override
    public void setProductLimit(Integer productId, Integer limit) throws SQLException {
        Product product = productDAO.get(productId);
        if (limit > 0 && !(limit < 0)) {
            product.setProductLimit(limit);
            product.setIsAvailable(false);
            productDAO.update(product);
        } else
            throw new NullPointerException();
    }

    @Override
    public void updateProduct(Integer oldValueId,ProductDTO newValue) throws SQLException {
        Product product = productDAO.get(oldValueId);
        if (product != null) {
            product.setInfo(newValue.getInfo());
            product.setName(newValue.getName());
            product.setPrice(newValue.getPrice());
            product.setIsAvailable(newValue.getIsAvailable());
            productDAO.update(product);
        }
    }

    @Override
    public void addProduct(ProductDTO productDTO) throws SQLException {
        Product product = productMapper.mapToEntity(productDTO);
        productDAO.save(product);
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) throws SQLException {
        Product product = productDAO.get(productDTO.getId());
        productDAO.delete(product);
    }

    @Override
    public ProductDTO findProductById(Serializable id) throws SQLException {
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
    public Set<ProductDTO> getAllProductsFromCategory(Integer categoryId) throws SQLException {
        Category category = categoryDAO.get(categoryId);
        return productMapper.mapToSetDTOS(category.getProducts());
    }


}
