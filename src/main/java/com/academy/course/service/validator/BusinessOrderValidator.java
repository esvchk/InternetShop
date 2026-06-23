package com.academy.course.service.validator;

import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;

import java.sql.SQLException;

public interface BusinessOrderValidator {

    void validateGetAllOrders();
    void validateAddProductToOrder(Integer productId,Integer orderId, Integer quantity) throws SQLException;
}
