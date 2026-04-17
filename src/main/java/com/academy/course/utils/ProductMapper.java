package com.academy.course.utils;

import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

public class ProductMapper {

    public ProductDTO mapToProductDTO (Product product){
       return ProductDTO.builder()
                .name(product.getName())
                .price(product.getPrice())
                .info(product.getInfo())
                .build();
    }
    public Product mapToProduct(ProductDTO productDTO){
        return Product.builder()
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .info(productDTO.getInfo())
                .build();
    }
}
