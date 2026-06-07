package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Set;

public interface OrderService {

    OrderDTO findOrderById(Serializable orderId) throws SQLException;
    Set<OrderDTO> getAllOrders();
    Set<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) throws SQLException;
    void buyOrder(OrderDTO orderDTO) throws SQLException;
    void deleteOrder(OrderDTO orderDTO) throws SQLException;
    void addProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity) throws SQLException;
    void deleteItemFromOrder(ItemDTO itemDTO,Integer orderId,Integer quantity) throws SQLException;

}
