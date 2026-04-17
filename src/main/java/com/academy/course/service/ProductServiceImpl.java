package com.academy.course.service;

import com.academy.course.dao.ProductDAOImpl;
import com.academy.course.dto.ProductDTO;
import com.academy.course.utils.ProductMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductDAOImpl productDAO = new ProductDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();

    @Override
    public List<ProductDTO> findProductsByName(String name) {
        return productDAO.getAllProducts().stream()
                .filter(product -> product.getName().equals(name))
                .map(productMapper::mapToProductDTO)
                .collect(Collectors.toList());
    }
}
