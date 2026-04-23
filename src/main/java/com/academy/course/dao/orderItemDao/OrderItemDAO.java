package com.academy.course.dao.orderItemDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.util.Set;

public interface OrderItemDAO extends DAO<OrderItem> {
    Set<OrderItem> getProductsOfOrder(Order order);
    void deleteProductOfOrder(Integer productId,Integer orderId) throws SQLException;
}
