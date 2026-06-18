package com.academy.course.service.validator;


import com.academy.course.utils.Discount;

import java.sql.SQLException;

public interface BusinessItemValidator {
    void getAllItems();
    void getAllItemsFromOrder(Integer orderId) throws SQLException;
    void setDiscountOnItem(Discount discount) throws SQLException;
}
