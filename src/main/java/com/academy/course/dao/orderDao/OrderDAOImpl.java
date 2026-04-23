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
    public void addProductsToOrder(Product product, Order order, Integer quantity) throws SQLException {
        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .order(order)
                .quantity(quantity)
                .build();

        order.getOrderItems().add(orderItem);
        update(order);
    }

    @Override
    public void updateProductsOfOrder(Product product, Order order) {

    }

    @Override
    public void deleteProductFromOrder(Product product,Order order) throws SQLException {
        order.getOrderItems().remove(product);
        delete(product);
    }


}
