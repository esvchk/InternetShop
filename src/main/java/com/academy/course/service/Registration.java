package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.model.Customer;

import java.sql.SQLException;

public class Registration {

    private final CustomerService customerService = new CustomerServiceImpl();


    public boolean register(CustomerDTO customerDTO) throws SQLException {
        Customer customer = customerService.mapToCustomer(customerDTO);
        if (customer == null) {
            return true;
        } else
            return false;
    }

    public boolean tryToLogin(String login) {
        if (customerService.findCustomerByLogin(login) != null) {
            return false;
        } else
            return true;

    }
}
