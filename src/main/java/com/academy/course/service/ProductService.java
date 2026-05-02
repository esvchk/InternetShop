package com.academy.course.service;

import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface ProductService {
    void updateProduct(ProductDTO productDTO) throws SQLException;
    void addProduct(ProductDTO productDTO) throws SQLException;
    void deleteProduct(ProductDTO productDTO) throws SQLException;
    ProductDTO findProductById(Serializable id) throws SQLException;
    List<ProductDTO> findProductsByName(String name);
    List<ProductDTO> findAllProducts();
}
