package com.academy.course.service;


import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.OrderItemDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Order;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderDAOImpl orderDAO = new OrderDAOImpl();
    private final OrderItemService orderItemService = new OrderItemServiceImpl();
    private final ProductService productService = new ProductServiceImpl();


    @Override
    public OrderDTO mapToOrderDTO(Order order) {
        return OrderDTO.builder()
                .id(order.getId())
                .customer(order.getCustomer())
                .dateTimeOfCreation(LocalDateTime.now())
                .isBought(false)
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
    public Set<OrderItemDTO> getAllProductsFromOrder(OrderDTO orderDTO) {
        return orderDAO.getAllProductsFromOrder(mapToOrder(orderDTO)).stream()
                .map(orderItemService::mapToOrderItemDTO)
                .collect(Collectors.toSet());
    }

    @Override
    public void addProductToOrder(ProductDTO productDTO, OrderDTO orderDTO, Integer quantity) throws SQLException {
        orderDAO.addProductToOrder(productService.mapToProduct(productDTO),this.mapToOrder(orderDTO),quantity);
    }

    @Override
    public void updateProductOfOrder(ProductDTO oldValue, ProductDTO newValue, OrderDTO orderDTO, Integer quantity) throws SQLException {
        orderDAO.updateProductOfOrder(productService.mapToProduct(oldValue),productService.mapToProduct(newValue),
                this.mapToOrder(orderDTO),quantity);
    }

    @Override
    public void deleteProductFromOrder(ProductDTO productDTO, OrderDTO orderDTO) throws SQLException {
        orderDAO.deleteProductFromOrder(productService.mapToProduct(productDTO),this.mapToOrder(orderDTO));
    }
}
