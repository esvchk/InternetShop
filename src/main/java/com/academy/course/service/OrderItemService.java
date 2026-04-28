package com.academy.course.service;

import com.academy.course.dto.OrderItemDTO;
import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;

public interface OrderItemService {
    OrderItemDTO mapToOrderItemDTO(OrderItem orderItem);
    OrderItem mapToOrderItem(OrderItemDTO orderItemDTO);

}
