package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Customer;
import com.academy.course.model.Item;
import com.academy.course.model.Order;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface OrderService {

    OrderDTO findOrderById(Serializable orderId) throws SQLException;
    Set<OrderDTO> getAllOrders();
    Set<ItemDTO> getAllProductsFromOrder(OrderDTO orderDTO);
    void deleteOrder(OrderDTO orderDTO) throws SQLException;

    void addProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity) throws SQLException;
    void updateProductOfOrder(ProductDTO oldValue,ProductDTO newValue,OrderDTO order,Integer quantity) throws SQLException;
    void deleteProductFromOrder(ProductDTO productDTO,OrderDTO orderDTO) throws SQLException;


}
