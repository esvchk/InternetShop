package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAOImpl;

import com.academy.course.model.Item;
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
    public List<Item> getAllProductsFromOrder(Order order) {
        return order.getItems();
    }

    @Override
    public void addProductToOrder(Product product, Order order, Integer quantity) throws SQLException {
        Item item = Item.builder()
                .product(product)
                .productQuantity(quantity)
                .order(order)
                .build();

        order.getItems().add(item);
        update(order);
    }


    @Override
    public void updateProductOfOrder(Product oldValue, Product newValue, Order order, Integer quantity) throws SQLException {
        Item item = order.getItems().stream().filter(item1 -> item1.getProduct().equals(oldValue))
                        .findFirst().orElse(null);

        Item newItem = Item.builder()
                .productQuantity(quantity)
                .product(newValue)
                .order(order)
                .build();

        order.getItems().set(item.getId(), newItem);
        update(order);
    }


    @Override
    public void deleteProductFromOrder(Product product, Order order) throws SQLException {
        order.getItems().removeIf(item -> item.getProduct().equals(product));
        update(order);
    }


}

