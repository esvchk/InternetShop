package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;
import com.academy.course.model.Product;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface OrderDAO extends DAO<Order> {

    List<Order> getAllOrders();
    void addProductsToOrder(Product product, Order order, Integer quantity) throws SQLException;
    void updateProductsOfOrder(Product product,Order order);
    void deleteProductFromOrder(Product product,Order order) throws SQLException;
}
