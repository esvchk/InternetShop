package com.academy.course.service;


import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.*;
import com.academy.course.model.Item;
import com.academy.course.model.Order;
import com.academy.course.model.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();
    private final ItemMapper itemMapper = new ItemMapper(productMapper);
    private final OrderMapper orderMapper = new OrderMapper(itemMapper);


    @Override
    public OrderDTO findOrderById(Serializable orderId) throws SQLException {
        return orderMapper.mapToDTO(orderDAO.get(orderId));
    }

    @Override
    public Set<OrderDTO> getAllOrders() {
        return orderMapper.mapToListDTOS(orderDAO.getAllOrders());
    }

    @Override
    public Set<ItemDTO> getAllProductsFromOrder(OrderDTO orderDTO) {
        return itemMapper.mapToListDTOS(orderMapper.mapToEntity(orderDTO).getItems());

    }

    @Override
    public void deleteOrder(OrderDTO orderDTO) throws SQLException {
        if (orderDTO.getId() != null) {
            orderDAO.delete(orderDAO.get(orderDTO.getId()));
        } else
            throw new NullPointerException();
    }

    @Override
    public void addProductToOrder(ProductDTO productDTO,
                                  OrderDTO orderDTO, Integer quantity) throws SQLException {
        if (productDTO.getId() != null && orderDTO.getId() != null) {
            Product product = productDAO.get(productDTO.getId());
            Order order = orderDAO.get(orderDTO.getId());
            Item item = Item.builder()
                    .productQuantity(quantity)
                    .product(product)
                    .order(order)
                    .build();
            order.getItems().add(item);
            orderDAO.update(order);
        } else
            throw new NullPointerException();
    }

    @Override
    public void updateProductOfOrder(ProductDTO oldValue, ProductDTO newValue,
                                     OrderDTO orderDTO, Integer quantity) throws SQLException {
        orderDAO.updateProductOfOrder(productMapper.mapToEntity(oldValue),
                productMapper.mapToEntity(newValue), orderMapper.mapToEntity(orderDTO), quantity);
    }

    @Override
    public void deleteProductFromOrder(ProductDTO productDTO, OrderDTO orderDTO) throws SQLException {
        orderDAO.deleteProductFromOrder(productMapper.mapToEntity(productDTO), orderMapper.mapToEntity(orderDTO));
    }


}
