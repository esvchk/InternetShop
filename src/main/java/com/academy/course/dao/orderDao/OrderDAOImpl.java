package com.academy.course.dao.orderDao;

import com.academy.course.dao.DAOImpl;

import com.academy.course.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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



}

