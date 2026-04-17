package com.academy.course.service;

import com.academy.course.dao.ProductDAOImpl;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductDAOImpl productDAO = new ProductDAOImpl();

    @Override
    public List<ProductDTO> findProductsByName(String name) {
        return productDAO.getAllProducts().stream()
                .filter(product -> product.getName().equals(name))
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productDAO.getAllProducts().stream()
                .map(this::mapToProductDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Product mapToProduct(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .info(productDTO.getInfo())
                .build();
    }

    @Override
    public ProductDTO mapToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .info(product.getInfo())
                .build();
    }
}
