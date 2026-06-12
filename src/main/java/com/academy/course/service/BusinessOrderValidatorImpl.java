package com.academy.course.service;

import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BusinessOrderValidatorImpl implements BusinessOrderValidator,EmptyFieldValidator<String>{
    private static final Logger logger = LogManager.getLogger(BusinessOrderValidator.class);
    private final OrderDAO orderDAO;

    public BusinessOrderValidatorImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public void validateBuyOrder(OrderDTO orderDTO) {
        if (orderDTO.getIsBought() == true) {
            logger.warn("Attempt to buy order with id {} failed",orderDTO.getId());
            throw new OrderPurchasingException(orderDTO.getId());
        }
    }

    @Override
    public void validateAddProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity) {
        validateField(String.valueOf(quantity));
        validateField(String.valueOf(orderDTO.getIsBought()));
        if (orderDTO.getIsBought() == true) {
            logger.warn("Try to change bought order");
            throw new ProductAddingException("Try to change bought order");
        }
    }

    @Override
    public void validateDeleteItemFromOrder(ItemDTO itemDTO, Integer orderId, Integer quantity) {
        validateField(String.valueOf(quantity));
        if (itemDTO.getQuantity() < quantity ) {
            logger.warn("Try to delete with bigger quantity{}",quantity);
            throw new WrongValueException("Input " + quantity + " cannot be higher than remained products",
                    String.valueOf(itemDTO.getQuantity()));
        }
    }

    @Override
    public void validateGetAllOrders() {
        if (orderDAO.getAllOrders() == null ||orderDAO.getAllOrders().isEmpty()) {
            logger.warn("Empty list of orders");
            throw new EmptyListException("Order");
        }
    }

    @Override
    public void validateField(String fieldName) {
        if (fieldName == null||fieldName.trim().isEmpty()) {
            logger.warn("Empty field{}",fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
