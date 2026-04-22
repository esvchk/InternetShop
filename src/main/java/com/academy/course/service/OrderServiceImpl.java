package com.academy.course.service;

import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Order;

import java.sql.SQLException;
import java.util.HashSet;

public class OrderServiceImpl implements OrderService {

    private final OrderDAOImpl orderDAO = new OrderDAOImpl();

    @Override
    public Order mapToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .products(orderDTO.getProducts())
                .customers(new HashSet<>())
                .operator(orderDTO.getOperator())
                .build();
    }

    @Override
    public OrderDTO mapToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .operator(order.getOperator())
                .products(order.getProducts())
                .build();
    }

    @Override
    public void addProduct(OrderDTO orderDTO) throws SQLException {
        Order order = mapToOrder(orderDTO);
        orderDAO.save(order);
    }
}
