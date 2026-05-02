package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface CustomerService {
    void createOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException;
    void deleteOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException;
    Customer mapToCustomer(CustomerDTO customerDTO);
    CustomerDTO mapToCustomerDTO(Customer customer);
    List<CustomerDTO> getAllCustomers();
    Set<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO);
    void buyOrder(CustomerDTO customerDTO,OrderDTO orderDTO);
    CustomerDTO findCustomerById(Integer id) throws SQLException;
    CustomerDTO findCustomerByLogin(String login);
    String getPassOfCustomerByLogin(String login);
    void createCustomer(CustomerDTO customerDTO) throws SQLException;
    CustomerDTO getCustomer(Integer id) throws SQLException;
    void updateCustomer(CustomerDTO customerDTO,String newPassWord) throws SQLException;
    void deleteCustomer(CustomerDTO customerDTO) throws SQLException;
}
