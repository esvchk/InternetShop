package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Order;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO extends DAO<Order> {

    List<Order> getAllOrders();
    List<Product> getAllProductsFromOrder(Order order);
    void addProductToOrder(Product product,Order order,Integer quantity) throws SQLException;
    void updateProductOfOrder(Product oldValue,Product newValue,Order order,Integer quantity) throws SQLException;
    void deleteProductFromOrder(Product product,Order order) throws SQLException;

}
