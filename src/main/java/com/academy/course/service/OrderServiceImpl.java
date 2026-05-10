package com.academy.course.service;


import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Order;
import com.academy.course.utils.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService, Mapper<Order,OrderDTO> {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductServiceImpl productService = new ProductServiceImpl();
    private final ItemServiceImpl itemService = new ItemServiceImpl();


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
    public List<ItemDTO> getAllProductsFromOrder(OrderDTO orderDTO) {
        return orderDAO.getAllProductsFromOrder(this.mapToEntity(orderDTO)).stream()
                .map(itemService::mapToDTO)
                .collect(Collectors.toList());
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
    public OrderDTO mapToDTO(Order entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .productDTO(null)
                .customerDTO(null)
                .isBought(null)
                .build();
    }

    @Override
    public Order mapToEntity(OrderDTO dto) {
        return Order.builder()
                .items(null)
                .customer(null)
                .build();
    }
}
