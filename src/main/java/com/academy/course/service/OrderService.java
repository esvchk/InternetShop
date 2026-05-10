package com.academy.course.service;

import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Item;

import java.sql.SQLException;
import java.util.List;

public interface OrderService {

    OrderDTO findOrderById(Integer orderId) throws SQLException;
    List<OrderDTO> getAllOrders();
    List<ItemDTO> getAllProductsFromOrder(OrderDTO order);
    void addProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity) throws SQLException;
    void updateProductOfOrder(ProductDTO oldValue,ProductDTO newValue,OrderDTO order,Integer quantity) throws SQLException;
    void deleteProductFromOrder(ProductDTO productDTO,OrderDTO orderDTO) throws SQLException;


}
