package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAOImpl;

import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.util.*;

public class OrderDAOImpl extends DAOImpl<Order> implements OrderDAO {


    public OrderDAOImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> getAllOrders() {
        return getEm().createQuery("from Order order", Order.class).getResultList();
    }

    @Override
    public void addProductToOrder(Product product, Order order, Integer quantity) throws SQLException {
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .order(order)
                .quantity(quantity)
                .build();
        order.getOrderItems().add(orderItem);
        update(order);
    }


    @Override
    public void updateProductOfOrder(Product oldValue, Product newValue, Order order,Integer quantity) throws SQLException {

        OrderItem orderItem = order.getOrderItems().stream()
                        .filter(orderItem1 -> orderItem1.getProduct().equals(oldValue))
                                .findFirst().orElse(null);

        orderItem.setOrder(order);
        orderItem.setProduct(newValue);
        orderItem.setQuantity(quantity);
        update(order);

    }


    @Override
    public void deleteProductFromOrder(Product product, Order order) throws SQLException {
        OrderItem orderItem1 = order.getOrderItems().stream()
                .filter(orderItem -> orderItem.getProduct().equals(product))
                .findFirst().orElse(null);
        order.getOrderItems().remove(orderItem1);
        update(order);
    }


}

