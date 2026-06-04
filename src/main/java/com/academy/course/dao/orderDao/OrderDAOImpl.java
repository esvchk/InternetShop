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
    public Set<Item> getAllProductsFromOrder(Order order) {
        return order.getItems();
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
    public void updateProductOfOrder(Product oldValue, Product newValue, Order order, Integer quantity) throws
            SQLException {
        Item item = order.getItems().stream().filter(item1 -> item1.getProduct().getId().equals(oldValue.getId()))
                .findFirst().orElse(null);

        Item newItem = Item.builder()
                .productQuantity(quantity)
                .product(newValue)
                .order(order)
                .build();

        order.getItems().remove(item);
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

    @Override
    public boolean buyOrder(Integer orderId) throws SQLException {
        if (orderId != null) {
            Order order = get(orderId);
            if (order.getIsBought() == null || !order.getIsBought()) {
                order.setIsBought(true);
                update(order);
                return true;
            } else
                log.warn("Order number {} already has been purchased", orderId);
        }
        return false;
    }
}

