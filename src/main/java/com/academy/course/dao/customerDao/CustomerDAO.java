package com.academy.course.dao.customerDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;
import com.thoughtworks.qdox.model.expression.Or;

import java.sql.SQLException;

public interface CustomerDAO extends DAO<Customer> {
    void createOrder(Customer customer, Order order) throws SQLException;
}
