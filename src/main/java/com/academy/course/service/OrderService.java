package com.academy.course.service;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.utils.Discount;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Set;

public interface OrderService {

    OrderDTO findOrderById(Integer orderId) throws SQLException;
    Set<OrderDTO> getAllOrdersWithItems();
    void buyOrder(OrderDTO orderDTO) throws SQLException;
    void deleteOrder(Integer id) throws SQLException;
    void addProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity) throws SQLException;
    void deleteItemFromOrder(ItemDTO itemDTO,Integer orderId,Integer quantity) throws SQLException;
    Double countAmountOfAllItems(OrderDTO orderDTO) throws SQLException;
    void setDiscountOnOrder(Integer orderId, Discount discount) throws SQLException;

}
