package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;

import java.sql.SQLException;

public class RegistrationService {

    private final CustomerService customerService = new CustomerServiceImpl();

    public boolean register(CustomerDTO customerDTO) throws SQLException {
        customerService.createCustomer(customerDTO);
        return true;
    }

    public boolean tryToLogin(String login) {
        if (customerService.findCustomerByLogin(login) != null) {
            return false;
        } else
            return true;

    }
}
