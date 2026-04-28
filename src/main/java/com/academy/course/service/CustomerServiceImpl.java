package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;

import java.sql.SQLException;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    public void createOrder(Customer customer, Order order) throws SQLException {
        customerService.createOrder(customer, order);
    }

    @Override
    public void deleteOrder(Customer customer, Order order) throws SQLException {

    }

    @Override
    public Customer mapToCustomer(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        return Customer.builder()
                .login(customerDTO.getLogin())
                .passWord(customer.getPassWord())
                .email(customerDTO.getEmail())
                .payment(customerDTO.getPayment())
                .orders(customer.getOrders())
                .build();
    }

    @Override
    public CustomerDTO mapToCustomerDTO(Customer customer) {
        CustomerDTO customerDTO = new CustomerDTO();
        return CustomerDTO.builder()
                .id(customer.getId())
                .login(customer.getLogin())
                .email(customer.getLogin())
                .payment(customer.getPayment())
                .dateTimeOfOrder(customerDTO.getDateTimeOfOrder())
                .build();
    }
}
