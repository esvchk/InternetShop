package com.academy.course.service.validator;

import com.academy.course.dto.ProductDTO;

import java.util.Set;

public interface BasePaginationValidation {
     void validatePagination(Integer offSet, Integer size, Long totalSize,Integer setSize);
}
