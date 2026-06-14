package com.academy.course.service;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Set;

public interface ProductService {
    void setProductLimit(ProductDTO productDTO,Integer limit) throws SQLException;
    void updateProduct(Integer oldValueId,ProductDTO newValue) throws SQLException;
    void addProduct(ProductDTO productDTO) throws SQLException;
    void deleteProduct(ProductDTO productDTO) throws SQLException;
    ProductDTO getProductById(Integer id) throws SQLException;
    ProductDTO findProductsByName(String name);
    Set<ProductDTO> getAllProducts();
    Set<ProductDTO> getAllProductsFromCategory(CategoryDTO categoryDTO) throws SQLException;
}
