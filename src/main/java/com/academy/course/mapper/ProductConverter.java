package com.academy.course.mapper;

import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductConverter {

    ProductConverter INSTANCE = Mappers.getMapper(ProductConverter.class);

    @Mapping(source = "category",target = "categoryDTO")
    ProductDTO toDto(Product product);

    @Mapping(source = "categoryDTO",target = "category")
    Product toProduct(ProductDTO productDTO);
}
