package com.academy.course.mapper;

import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;

import java.util.ArrayList;
import java.util.List;

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
                .isAvailable(null)
                .build();
    }

    @Override
    public List<Product> mapToListEntities(List<ProductDTO> dtoList) {
        List<Product> list = new ArrayList<>();
        for (ProductDTO productDTO: dtoList){
            Product product = Product.builder()
                    .name(productDTO.getName())
                    .price(productDTO.getPrice())
                    .info(productDTO.getInfo())
                    .isAvailable(null)
                    .build();
            list.add(product);
        }
        return list;
    }

    @Override
    public List<ProductDTO> mapToListDTOS(List<Product> entityList) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product product : entityList){
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
