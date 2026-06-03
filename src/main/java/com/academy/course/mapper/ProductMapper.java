package com.academy.course.mapper;

import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductMapper implements Mapper<Product, ProductDTO>{
    @Override
    public ProductDTO mapToDTO(Product entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .info(entity.getInfo())
                .build();
    }

    @Override
    public Product mapToEntity(ProductDTO dto) {
        return Product.builder()
                .name(dto.getName())
                .price(dto.getPrice())
                .info(dto.getInfo())
                .isAvailable(dto.getIsAvailable())
                .build();
    }

    @Override
    public Set<Product> mapToListEntities(Set<ProductDTO> dtoSet) {
        Set<Product> list = new HashSet<>();
        for (ProductDTO productDTO: dtoSet){
            Product product = Product.builder()
                    .name(productDTO.getName())
                    .price(productDTO.getPrice())
                    .info(productDTO.getInfo())
                    .build();
            list.add(product);
        }
        return list;
    }

    @Override
    public Set<ProductDTO> mapToListDTOS(Set<Product> entitySet) {
        Set<ProductDTO> productDTOS = new HashSet<>();
        for (Product product : entitySet){
            ProductDTO productDTO = ProductDTO.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .price(product.getPrice())
                    .info(product.getInfo())
                    .build();
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
