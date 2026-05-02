package com.academy.course.service;


import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.OrderItemDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;
import com.academy.course.utils.ObjectMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService, ObjectMapper<Order, OrderDTO> {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final OrderItemServiceImpl orderItemService = new OrderItemServiceImpl();
    private final ProductServiceImpl productService = new ProductServiceImpl();


    @Override
    public OrderDTO findOrderById(Integer orderId) throws SQLException {
        return mapToDTO(orderDAO.get(orderId));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderDAO.getAllOrders().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Set<OrderItemDTO> getAllProductsFromOrder(OrderDTO orderDTO) {
        return orderDAO.getAllProductsFromOrder(mapToEntity(orderDTO)).stream()
                .map(orderItemService::mapToDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public void addProductToOrder(ProductDTO productDTO,
                                  OrderDTO orderDTO, Integer quantity) throws SQLException {
        orderDAO.addProductToOrder(productService.mapToEntity(productDTO),
                this.mapToEntity(orderDTO), quantity);
    }

    @Override
    public void updateProductOfOrder(ProductDTO oldValue, ProductDTO newValue,
                                     OrderDTO orderDTO, Integer quantity) throws SQLException {
        orderDAO.updateProductOfOrder(productService.mapToEntity(oldValue),
                productService.mapToEntity(newValue),
                this.mapToEntity(orderDTO), quantity);
    }

    @Override
    public void deleteProductFromOrder(ProductDTO productDTO, OrderDTO orderDTO) throws SQLException {
        orderDAO.deleteProductFromOrder(productService.mapToEntity(productDTO),
                this.mapToEntity(orderDTO));
    }


    @Override
    public OrderDTO mapToDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .customer(order.getCustomer())
                .build();
    }

    @Override
    public Order mapToEntity(OrderDTO orderDTO) {
        return Order.builder()
                .customer(orderDTO.getCustomer())
                .build();
    }
}
