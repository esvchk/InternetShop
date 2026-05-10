package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.exception.UserNotFound;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface CustomerService {
    void deleteOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException;
    List<CustomerDTO> getAllCustomers();
    List<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO);
    void buyOrder(CustomerDTO customerDTO,OrderDTO orderDTO);
    CustomerDTO findCustomerById(Integer id) throws SQLException;
    CustomerDTO findCustomerByLogin(String login);
    void createCustomer(CustomerDTO customerDTO) throws SQLException;
    void updateCustomer(CustomerDTO customerDTO,String newPassWord) throws SQLException;
    void deleteCustomer(CustomerDTO customerDTO) throws SQLException;
    void register(String login,String passWord) throws SQLException, UserNotFound;
    void login(String login,String passWord) throws NoSuchFieldException, SQLException, UserNotFound;

}
