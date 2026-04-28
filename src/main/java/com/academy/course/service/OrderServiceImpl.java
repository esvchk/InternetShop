package com.academy.course.service;

import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dao.orderItemDao.OrderItemDAOImpl;
import com.academy.course.dao.orderItemDao.OrderItemDao;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.OrderItemDTO;
import com.academy.course.model.Order;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderDAOImpl orderDAO = new OrderDAOImpl();
    private final OrderItemDao orderItemDao = new OrderItemDAOImpl();


    @Override
    public OrderDTO mapToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .customer(order.getCustomer())
                .build();
    }

    @Override
    public Order mapToOrder(OrderDTO orderDTO) {
        return Order.builder()
                .customer(orderDTO.getCustomer())
                .build();
    }

    @Override
    public OrderDTO findOrderById(Integer orderId) throws SQLException {
        return mapToOrderDTO(orderDAO.get(orderId));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderDAO.getAllOrders().stream()
                .map(this::mapToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Set<OrderItemDTO> getAllProductsFromOrder(Order order) {
        return orderDAO.getAllProductsFromOrder(order).stream()
                .map(orderItemDao::mapToOrderItemDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public void addProductToOrder(Product product, Order order, Integer quantity) throws SQLException {
        orderDAO.addProductToOrder(product,order,quantity);
    }

    @Override
    public void updateProductOfOrder(Product oldValue, Product newValue, Order order, Integer quantity) throws SQLException {
        orderDAO.updateProductOfOrder(oldValue,newValue,order,quantity);
    }

    @Override
    public void deleteProductFromOrder(Product product, Order order) throws SQLException {
        orderDAO.deleteProductFromOrder(product,order);
    }
}
