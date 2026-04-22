package com.academy.course.service;

import com.academy.course.dao.ProductDAOImpl;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductDAOImpl productDAO = new ProductDAOImpl();

    @Override
    public void updateProduct(ProductDTO productDTO) throws SQLException {
        Product product = productDAO.get(productDTO.getId());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        product.setInfo(productDTO.getInfo());
        productDAO.update(product);
    }

    @Override
    public void addProduct(ProductDTO productDTO) throws SQLException {
        Product product = mapToProduct(productDTO);
        productDAO.save(product);
    }

    @Override
    public void deleteProduct(Serializable id) throws SQLException {
        productDAO.delete(id);
    }

    @Override
    public ProductDTO findProductById(Serializable id) throws SQLException {
        return mapToProductDTO(productDAO.get(id));
    }

    @Override
    public List<ProductDTO> findProductsByName(String name) {
       return productDAO.getByName(name).stream()
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
                .manufacturer(productDTO.getManufacturer())
                .bestBefore(productDTO.getBestBefore())
                .build();
    }

    @Override
    public ProductDTO mapToProductDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .info(product.getInfo())
                .manufacturer(product.getManufacturer())
                .bestBefore(product.getBestBefore())
                .build();
    }
}
