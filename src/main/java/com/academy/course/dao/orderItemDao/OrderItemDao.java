package com.academy.course.dao.orderItemDao;

import com.academy.course.dao.DAO;
import com.academy.course.dto.OrderItemDTO;
import com.academy.course.model.OrderItem;

public interface OrderItemDao extends DAO<OrderItem> {
    OrderItemDTO mapToOrderItemDTO(OrderItem orderItem);
    OrderItem mapToOrderItem(OrderItemDTO orderItemDTO);
}
