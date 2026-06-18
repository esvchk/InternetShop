package com.academy.course.service.validator;

import com.academy.course.dto.ItemDTO;
import com.academy.course.utils.Discount;

import java.sql.SQLException;
import java.util.Set;

public interface BusinessItemValidator {
    void getAllItems();
    void getAllItemsFromOrder(Integer orderId) throws SQLException;
}
