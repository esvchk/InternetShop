package com.academy.course.mapper;

import com.academy.course.dto.CategoryDTO;
import com.academy.course.exception.EmptyEntityException;
import com.academy.course.model.Category;
import com.academy.course.service.validator.EmptyFieldValidator;

import java.util.HashSet;
import java.util.Set;

public class CategoryMapper implements Mapper<Category,CategoryDTO>, EmptyFieldValidator<Object> {

    private final ProductMapper productMapper;

    public CategoryMapper(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }


    @Override
    public CategoryDTO mapToDTO(Category entity) {
        validateField(entity);
        return CategoryDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .productsDTO(productMapper.mapToSetDTOS(entity.getProducts()))
                .build();
    }

    @Override
    public Category mapToEntity(CategoryDTO dto) {
        validateField(dto);
        return Category.builder()
                .name(dto.getName())
                .products(productMapper.mapToSetEntities(dto.getProductsDTO()))
                .build();
    }

    @Override
    public Set<Category> mapToSetEntities(Set<CategoryDTO> dtoSet) {
        Set<Category> list = new HashSet<>();
        for (CategoryDTO categoryDTO : dtoSet){
            Category category = Category.builder()
                    .name(categoryDTO.getName())
                    .products(productMapper.mapToSetEntities(categoryDTO.getProductsDTO()))
                    .build();
            list.add(category);
        }
        return list;
    }

    @Override
    public Set<CategoryDTO> mapToSetDTOS(Set<Category> entitySet) {
        Set<CategoryDTO> list = new HashSet<>();
        for (Category category : entitySet){
            CategoryDTO categoryDTO = CategoryDTO.builder()
                    .id(category.getId())
                    .name(category.getName())
                    .productsDTO(productMapper.mapToSetDTOS(category.getProducts()))
                    .build();
            list.add(categoryDTO);
        }
        return list;
    }

    @Override
    public void validateField(Object object) {
        if (object == null) {
            throw new EmptyEntityException(object);
        }
    }
}
