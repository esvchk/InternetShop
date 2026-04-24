package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAOImpl;
import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;
import com.academy.course.model.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.*;

public class OrderDAOImpl extends DAOImpl<Order> implements OrderDAO {


    private final ProductDAO productDAO = new ProductDAOImpl();

    public OrderDAOImpl() {
        super(Order.class);
    }

    @Override
    public List<Order> getAllOrders() {
        return getEm().createQuery("from Order order", Order.class).getResultList();
    }

    @Override
    public void addOrderItemToOrder(OrderItem orderItem, Order order, Integer quantity) throws SQLException {
        order.getOrderItems().add(orderItem);
        update(order);
    }


    @Override
    public void updateOrderItemOfOrder(OrderItem newOrderItem, OrderItem orderItemToRemove, Order order) throws SQLException {
        boolean isExist = order.getOrderItems().contains(orderItemToRemove);
        if (isExist) {
            order.getOrderItems().remove(orderItemToRemove);
            order.getOrderItems().add(newOrderItem);
            update(order);
        } else
            throw new RuntimeException();
    }


    @Override
    public void deleteOrderItemFromOrder(OrderItem orderItem, Order order) throws SQLException {

        order.getOrderItems().remove(orderItem);
        update(order);

    }
}

