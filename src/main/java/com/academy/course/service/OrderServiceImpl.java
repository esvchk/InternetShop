package com.academy.course.service;


import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.*;
import com.academy.course.model.Order;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();
    private final ItemMapper itemMapper = new ItemMapper(productMapper);
    private final CustomerMapper customerMapper = new CustomerMapper();
    private final OrderMapper orderMapper = new OrderMapper(itemMapper,customerMapper);


    @Override
    public OrderDTO findOrderById(Serializable orderId) throws SQLException {
        return orderMapper.mapToDTO(orderDAO.get(orderId));
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderMapper.mapToListDTOS(orderDAO.getAllOrders());
    }

    @Override
    public List<ItemDTO> getAllProductsFromOrder(OrderDTO orderDTO) {
        return itemMapper.mapToListDTOS(orderMapper.mapToEntity(orderDTO).getItems());

    }

    @Override
    public void addProductToOrder(ProductDTO productDTO,
                                  OrderDTO orderDTO, Integer quantity) throws SQLException {
        orderDAO.addProductToOrder(productMapper.mapToEntity(productDTO),
                orderMapper.mapToEntity(orderDTO), quantity);
    }

    @Override
    public void updateProductOfOrder(ProductDTO oldValue, ProductDTO newValue,
                                     OrderDTO orderDTO, Integer quantity) throws SQLException {
        orderDAO.updateProductOfOrder(productMapper.mapToEntity(oldValue),
                productMapper.mapToEntity(newValue), orderMapper.mapToEntity(orderDTO), quantity);
    }

    @Override
    public void deleteProductFromOrder(ProductDTO productDTO, OrderDTO orderDTO) throws SQLException {
        orderDAO.deleteProductFromOrder(productMapper.mapToEntity(productDTO),orderMapper.mapToEntity(orderDTO));
    }


}
