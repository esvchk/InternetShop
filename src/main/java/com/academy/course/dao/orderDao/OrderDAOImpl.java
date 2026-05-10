package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAOImpl;

import com.academy.course.model.Order;
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
    public List<Product> getAllProductsFromOrder(Order order) {
        return order.getProducts();
    }

    @Override
    public void addProductToOrder(Product product, Order order, Integer quantity) throws SQLException {
        order.getProducts().add(product);
        update(order);
    }


    @Override
    public void updateProductOfOrder(Product oldValue, Product newValue, Order order,Integer quantity) throws SQLException {
        order.getProducts().set(oldValue.getId(),newValue);
        update(order);
    }


    @Override
    public void deleteProductFromOrder(Product product, Order order) throws SQLException {
        order.getProducts().remove(product);
        update(order);
    }


}

