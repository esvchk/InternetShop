package com.academy.course.dao.customerDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;
import com.thoughtworks.qdox.model.expression.Or;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface CustomerDAO extends DAO<Customer> {
    void createOrder(Customer customer, Order order) throws SQLException;
    void deleteOrder(Customer customer, Order order) throws SQLException;
    Customer getCustomerByLogin(String login);
    String getPassByLogin(String login);
    List<Customer> getAllCustomers();
    Set<Order> getAllOrdersOfCustomer(Customer customer);
}
