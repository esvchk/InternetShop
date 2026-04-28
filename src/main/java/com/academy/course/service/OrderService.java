package com.academy.course.service;

import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.OrderItemDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface OrderService {

    OrderDTO mapToOrderDTO(Order order);
    Order mapToOrder(OrderDTO orderDTO);
    OrderDTO findOrderById(Integer orderId) throws SQLException;
    List<OrderDTO> getAllOrders();
    Set<OrderItemDTO> getAllProductsFromOrder(OrderDTO order);
    void addProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity) throws SQLException;
    void updateProductOfOrder(ProductDTO oldValue,ProductDTO newValue,OrderDTO order,Integer quantity) throws SQLException;
    void deleteProductFromOrder(ProductDTO productDTO,OrderDTO orderDTO) throws SQLException;


}
