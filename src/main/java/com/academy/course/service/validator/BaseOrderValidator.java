package com.academy.course.service.validator;

import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;

public interface BaseOrderValidator{
    void validateBuyOrder(OrderDTO orderDTO);
    void validateAddProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity);
    void validateDeleteItemFromOrder(ItemDTO itemDTO, Integer orderId, Integer quantity);
}
