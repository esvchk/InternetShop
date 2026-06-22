package com.academy.course.service;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.paginator.PaginatedResult;

import java.sql.SQLException;
import java.util.Set;

public interface ProductService {
    PaginatedResult<ProductDTO> getPaginatedListOfProducts(int offset, int size);
    void setProductLimit(Integer id,Integer limit) throws SQLException;
    void updateProduct(Integer oldValueId,ProductDTO newValue) throws SQLException;
    void addProduct(ProductDTO productDTO) throws SQLException;
    void deleteProduct(Integer id) throws SQLException;
    ProductDTO getProductById(Integer id) throws SQLException;
    Set<ProductDTO> findProductsByName(String name);
    Set<ProductDTO> getAllProducts();
    Set<ProductDTO> getAllProductsFromCategory(CategoryDTO categoryDTO) throws SQLException;
}
