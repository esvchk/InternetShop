package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Order;
import com.academy.course.model.Product;

import java.util.List;

public interface OrderDAO extends DAO<Order> {

    List<Order> getAllOrders();

}
