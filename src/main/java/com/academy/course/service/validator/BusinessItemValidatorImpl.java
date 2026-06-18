package com.academy.course.service.validator;

import com.academy.course.dao.itemDao.ItemDAO;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.exception.EmptyFieldException;
import com.academy.course.exception.EmptyListException;
import com.academy.course.utils.Discount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;

public class BusinessItemValidatorImpl implements BusinessItemValidator,EmptyFieldValidator<String>{

    private static final Logger logger = LogManager.getLogger(BusinessItemValidatorImpl.class);

    private final ItemDAO itemDAO;
    private final OrderDAO orderDAO;

    public BusinessItemValidatorImpl(ItemDAO itemDAO, OrderDAO orderDAO) {
        this.itemDAO = itemDAO;
        this.orderDAO = orderDAO;
    }

    @Override
    public void validateField(String fieldName) {
        if (fieldName == null||fieldName.trim().isEmpty()) {
            logger.warn("Empty field {}",fieldName);
            throw new EmptyFieldException(fieldName);
        }
    }

    @Override
    public void getAllItems() {
        if (itemDAO.getAllItems() == null || itemDAO.getAllItems().isEmpty()) {
            logger.warn("Empty list of Items");
            throw new EmptyListException("Item");
        }
    }

    @Override
    public void getAllItemsFromOrder(Integer orderId) throws SQLException {
        if (orderDAO.get(orderId).getItems() == null ||orderDAO.get(orderId).getItems().isEmpty() ) {
            logger.warn("Empty list of Order with id {}",orderId);
            throw new EmptyListException("Item");
        }
    }

    @Override
    public void setDiscountOnItem(Discount discount) throws SQLException {
        if (discount == null) {
           throw new EmptyFieldException("Discount must have a value");
        }
    }
}
