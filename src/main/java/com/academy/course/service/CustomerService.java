package com.academy.course.service;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.OrderShortDTO;
import com.academy.course.exception.UserNotFound;
import com.academy.course.model.Customer;
import com.thoughtworks.qdox.model.expression.Or;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

public interface CustomerService {
    void deleteOrderOfCustomer(CustomerDTO customerDTO, OrderDTO orderDTO) throws SQLException;
    Set<CustomerDTO> getAllCustomersWithOrdersAndItems();
    Set<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) throws SQLException;
    void buyOrder(OrderDTO orderDTO) throws SQLException;
    CustomerDTO findCustomerById(Integer id) throws SQLException;
    CustomerDTO findCustomerByLogin(String login);
    void addNewOrderToCustomer(CustomerDTO customerDTO) throws SQLException;
    void createCustomer(CustomerDTO customerDTO,String pass) throws SQLException;
    void updateCustomer(Integer oldValueId,CustomerDTO newValue) throws SQLException;
    void deleteCustomer(CustomerDTO customerDTO) throws SQLException;
    boolean register(CustomerDTO customerDTO,String password) throws SQLException, UserNotFound;
    boolean login(String login,String passWord) throws NoSuchFieldException, SQLException, UserNotFound;

}
