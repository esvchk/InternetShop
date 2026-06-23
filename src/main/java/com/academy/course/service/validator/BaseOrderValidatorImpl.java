package com.academy.course.service.validator;

import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.OrderPurchasingException;
import com.academy.course.exception.ProductAddingException;
import com.academy.course.exception.WrongValueException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BaseOrderValidatorImpl implements BaseOrderValidator, EmptyFieldValidator<String> {

    private final static Logger logger = LogManager.getLogger(BaseOrderValidator.class);

    @Override
    public void validateBuyOrder(OrderDTO orderDTO) {
        if (orderDTO.getIsBought() == true) {
            logger.warn("Attempt to buy order with id {} failed", orderDTO.getId());
            throw new OrderPurchasingException(orderDTO.getId());
        }
    }



    @Override
    public void validateDeleteItemFromOrder(ItemDTO itemDTO, Integer orderId, Integer quantity) {
        validateField(String.valueOf(quantity));
        if (itemDTO.getQuantity() < quantity) {
            logger.warn("Try to delete with bigger quantity {}", quantity);
            throw new WrongValueException("Input " + quantity + " cannot be higher than remained products",
                    String.valueOf(itemDTO.getQuantity()));
        }
    }

    @Override
    public void validateField(String fieldName) {
        if (fieldName.equals("null") || fieldName.trim().isEmpty()) {
            logger.warn("Empty field{}", fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
