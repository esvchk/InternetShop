package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Order;

import java.util.Set;

public interface OrderDAO extends DAO<Order> {

    Set<Order> getAllOrders();

}
