package com.academy.course.service;

import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;
import com.academy.course.utils.Mapper;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService, Mapper<Product,ProductDTO> {

    private final ProductDAO productDAO = new ProductDAOImpl();

    @Override
    public void updateProduct(ProductDTO productDTO) throws SQLException {
        Product product = productDAO.get(productDTO.getId());
        product.setInfo(productDTO.getInfo());
        product.setName(productDTO.getName());
        product.setPrice(productDTO.getPrice());
        productDAO.update(product);
    }

    @Override
    public void addProduct(ProductDTO productDTO) throws SQLException {
        Product product = mapToEntity(productDTO);
        productDAO.save(product);
    }

    @Override
    public void deleteProduct(ProductDTO productDTO) throws SQLException {
        Product product = productDAO.get(productDTO.getId());
        productDAO.delete(product);
    }

    @Override
    public ProductDTO findProductById(Serializable id) throws SQLException {
        return mapToDTO(productDAO.get(id));
    }

    @Override
    public List<ProductDTO> findProductsByName(String name) {
        return productDAO.getByName(name).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDTO> findAllProducts() {
        return productDAO.getAllProducts().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public ProductDTO mapToDTO(Product product) {
        return ProductDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .info(product.getInfo())
                .build();
    }

    @Override
    public Product mapToEntity(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .info(productDTO.getInfo())
                .build();
    }
}
