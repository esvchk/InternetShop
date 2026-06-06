package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAOImpl;

import com.academy.course.model.Customer;
import com.academy.course.model.Item;
import com.academy.course.model.Order;
import com.academy.course.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.*;

public class OrderDAOImpl extends DAOImpl<Order> implements OrderDAO {


    private static final Logger log = LogManager.getLogger(OrderDAOImpl.class);

    public OrderDAOImpl() {
        super(Order.class);
    }

    @Override
    public Set<Order> getAllOrders() {
        return new HashSet<>(getEm().createQuery("from Order order", Order.class).getResultList());
    }

    @Override
    public void addProductToOrder(Product product, Order order, Integer quantity) throws SQLException {
        Item newItem = Item.builder()
                .product(product)
                .productQuantity(quantity)
                .order(order)
                .build();
        order.getItems().add(newItem);
        update(order);
    }



    @Override
    public void deleteProductFromOrder(Product product, Order order) throws SQLException {
        boolean isRemoved = order.getItems().removeIf(item -> item.getProduct() != null
                && item.getProduct().getId() != null && item.getProduct().getId().equals(product.getId()));
        if (isRemoved) {
            update(order);
        } else
            throw new NullPointerException();

    }

}

