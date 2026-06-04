package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Customer;
import com.academy.course.model.Item;
import com.academy.course.model.Order;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.util.Set;

public interface OrderDAO extends DAO<Order> {

    Set<Order> getAllOrders();
    Set<Item> getAllProductsFromOrder(Order order);
    boolean buyOrder(Integer id,Customer customer) throws SQLException;
    void addProductToOrder(Product product,Order order,Integer quantity) throws SQLException;
    void updateProductOfOrder(Product oldValue,Product newValue,Order order,Integer quantity) throws SQLException;
    void deleteProductFromOrder(Product product,Order order) throws SQLException;

}
