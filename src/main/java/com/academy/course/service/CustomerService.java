package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.OrderShortDTO;
import com.academy.course.exception.UserNotFound;
import com.thoughtworks.qdox.model.expression.Or;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface CustomerService {
    void deleteOrder(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException;
    void createEmptyOrder(CustomerDTO customerDTO) throws SQLException;
    Set<CustomerDTO> getAllCustomers();
    Set<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) throws SQLException;
    void buyOrder(CustomerDTO customerDTO,OrderDTO orderDTO) throws SQLException;
    CustomerDTO findCustomerById(Integer id) throws SQLException;
    CustomerDTO findCustomerByLogin(String login);
    void createCustomer(CustomerDTO customerDTO) throws SQLException;
    void updateCustomer(CustomerDTO customerDTO,String newPassWord) throws SQLException;
    void deleteCustomer(CustomerDTO customerDTO) throws SQLException;
    boolean register(String login,String passWord) throws SQLException, UserNotFound;
    boolean login(String login,String passWord) throws NoSuchFieldException, SQLException, UserNotFound;

}
