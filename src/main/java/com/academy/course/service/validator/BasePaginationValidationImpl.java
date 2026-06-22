package com.academy.course.service.validator;

import com.academy.course.exception.InvalidInputException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class BasePaginationValidationImpl implements BasePaginationValidation{

    private static final Logger logger = LogManager.getLogger(BasePaginationValidationImpl.class);
    @Override
    public void validatePagination(Integer offSet, Integer size, Long totalSize, Integer collectionSize) {
        List<String> errors = new ArrayList<>();
        if (offSet == null || offSet < 0) {
            errors.add("null or negative value for offset");
        }
        if (size == null || size < 0) {
            errors.add("null or negative value for size");
        }
        if (totalSize == null || totalSize < 1) {
            errors.add("total size cannot be empty or negative");
        }
        if (collectionSize == null || collectionSize < 1) {
            errors.add("collection can not be empty");
        }
        if (!errors.isEmpty()) {
            String message = String.join(",",errors);
            logger.warn("Input errors{}",errors);
            throw new InvalidInputException(message);
        }
    }
}
