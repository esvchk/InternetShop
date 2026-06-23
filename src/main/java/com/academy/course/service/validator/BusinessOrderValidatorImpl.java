package com.academy.course.service.validator;

import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.exception.*;
import com.academy.course.model.Order;
import com.academy.course.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BusinessOrderValidatorImpl implements BusinessOrderValidator,EmptyFieldValidator<String> {
    private static final Logger logger = LogManager.getLogger(BusinessOrderValidatorImpl.class);
    private final OrderDAO orderDAO;
    private final ProductDAO productDAO;

    public BusinessOrderValidatorImpl(OrderDAO orderDAO, ProductDAO productDAO) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
    }


    @Override
    public void validateGetAllOrders() {
        if (orderDAO.getAllOrders() == null ||orderDAO.getAllOrders().isEmpty()) {
            logger.warn("Empty list of orders");
            throw new EmptyListException("Order");
        }
    }

    @Override
    public void validateAddProductToOrder(Integer productId,Integer orderId, Integer quantity) throws SQLException {
        validateField(String.valueOf(quantity));
        List<String> errors = new ArrayList<>();
        Order order = orderDAO.get(orderId);
        Product product = productDAO.get(productId);
        if (order.getIsBought() == true) {
            logger.warn("Try to change bought order");
            errors.add("Try to change bought order");
        }
        if (product.getIsAvailable() == false) {
            logger.warn("Try to add product unavailable product{} to order{}", product, order);
            errors.add("This product has been baned" + product);
        }
        if (product.getProductLimit() != null && product.getProductLimit() < quantity) {
            logger.warn("Attempt to add quantity {} of products with a limit {}", quantity, product.getProductLimit());
            errors.add("Product limit " + product.getProductLimit() + " out of quantity " + quantity);
        }
        if (!errors.isEmpty()) {
            String message = String.join(",",errors);
            throw new ProductAddingException(message);
        }
    }

    @Override
    public void validateField(String fieldName) {
        if (fieldName.equals("null") || fieldName.trim().isEmpty()) {
            logger.warn("Empty field {}", fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }
}
