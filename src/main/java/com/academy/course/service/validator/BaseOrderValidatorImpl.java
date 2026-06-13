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
    public void validateAddProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity) {
        validateField(String.valueOf(quantity));
        List<String> errors = new ArrayList<>();
        if (orderDTO.getIsBought() == true) {
            logger.warn("Try to change bought order");
            errors.add("Try to change bought order");
        }
        if (productDTO.getIsAvailable() == false) {
            logger.warn("Try to add product unavailable product{} to order{}", productDTO, orderDTO);
            errors.add("This product has been baned" + productDTO);
        }
        if (productDTO.getProductLimit() != null && productDTO.getProductLimit() < quantity) {
            logger.warn("Attempt to add quantity {} of products with a limit {}", quantity, productDTO.getProductLimit());
            errors.add("Product limit " + productDTO.getProductLimit() + " out of quantity " + quantity);
        }
        if (!errors.isEmpty()) {
            String message = String.join(",",errors);
            throw new ProductAddingException(message);
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
        if (fieldName == null || fieldName.trim().isEmpty()) {
            logger.warn("Empty field{}", fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
