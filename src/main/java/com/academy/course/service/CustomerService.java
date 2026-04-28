package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;

import java.sql.SQLException;

public interface CustomerService {
    void createOrder(Customer customer, Order order) throws SQLException;
    void deleteOrder(Customer customer, Order order) throws SQLException;
    Customer mapToCustomer(CustomerDTO customerDTO);
    CustomerDTO mapToCustomerDTO(Customer customer);
}
