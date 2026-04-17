package com.academy.course.service;

import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

import java.util.List;

public interface ProductService {
    List<ProductDTO> findProductsByName(String name);
    List<ProductDTO> findAllProducts();
    Product mapToProduct(ProductDTO productDTO);
    ProductDTO mapToProductDTO (Product product);
}
