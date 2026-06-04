package com.academy.course.dao.customerDao;

import com.academy.course.dao.DAO;
import com.academy.course.exception.UserNotFound;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface CustomerDAO extends DAO<Customer> {
    void deleteOrderOfCustomer(Customer customer, Order order) throws SQLException;
    Customer getCustomerByLogin(String login);
    Set<Customer> getAllCustomers();
    Set<Order> getAllOrdersOfCustomer(Customer customer);
    void deleteCustomer(Integer id) throws SQLException;
    void createCustomer(Customer customer) throws SQLException;
    void updateCustomer(Integer oldValueId,Customer newValue) throws SQLException;
}
