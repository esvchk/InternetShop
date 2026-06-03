package com.academy.course.service;

import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.ProductMapper;
import com.academy.course.model.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO = new ProductDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public void updateProduct(ProductDTO productDTO) throws SQLException {
        Product product = productDAO.get(productDTO.getId());
        if (product != null) {
            product.setInfo(productDTO.getInfo());
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setIsAvailable(productDTO.getIsAvailable());
            productDAO.save(product);
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
    public Set<ProductDTO> findAllProducts() {
        return productMapper.mapToListDTOS(productDAO.getAllProducts());
    }


}
