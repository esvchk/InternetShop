package com.academy.course.service.validator;

import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.exception.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BusinessOrderValidatorImpl implements BusinessOrderValidator {
    private static final Logger logger = LogManager.getLogger(BusinessOrderValidator.class);
    private final OrderDAO orderDAO;

    public BusinessOrderValidatorImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }


    @Override
    public void validateGetAllOrders() {
        if (orderDAO.getAllOrders() == null ||orderDAO.getAllOrders().isEmpty()) {
            logger.warn("Empty list of orders");
            throw new EmptyListException("Order");
        }
    }

}
