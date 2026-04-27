package com.academy.course.service;

import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Order;

import java.sql.SQLException;
import java.util.HashSet;

public class OrderServiceImpl implements OrderService {

    private final OrderDAOImpl orderDAO = new OrderDAOImpl();


}
