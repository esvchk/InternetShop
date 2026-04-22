package com.academy.course.service;

import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Order;

import java.sql.SQLException;

public interface OrderService {

    Order mapToOrder(OrderDTO orderDTO);
    OrderDTO mapToOrderDTO(Order order);
    void addProduct(OrderDTO orderDTO) throws SQLException;
}
