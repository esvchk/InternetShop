package com.academy.course.service;

import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface ProductService {
    void updateProduct(Integer oldValueId,ProductDTO newValue) throws SQLException;
    void addProduct(ProductDTO productDTO) throws SQLException;
    void deleteProduct(ProductDTO productDTO) throws SQLException;
    ProductDTO findProductById(Serializable id) throws SQLException;
    ProductDTO findProductsByName(String name);
    Set<ProductDTO> getAllProducts();
    Set<ProductDTO> getAllProductsFromCategory(Integer categoryId) throws SQLException;
}
