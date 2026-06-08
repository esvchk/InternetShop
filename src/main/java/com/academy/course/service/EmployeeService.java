package com.academy.course.service;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.exception.UserNotFound;
import com.academy.course.utils.Role;

import java.sql.SQLException;
import java.util.Set;

public interface EmployeeService {

    void deleteOrderOfCustomer(EmployeeDTO employeeDTO, OrderDTO orderDTO) throws SQLException;
    Set<EmployeeDTO> getAllEmployeesWithOrdersAndItems();
    EmployeeDTO findEmployeeById(Integer id) throws SQLException;
    EmployeeDTO findEmployeeByLogin(String login);
    void addNewOrderToEmployee(EmployeeDTO employeeDTO) throws SQLException;
    void createEmployee(EmployeeDTO employeeDTO, String pass, Role role) throws SQLException;
    void updateEmployee(Integer oldValueId, EmployeeDTO newValue) throws SQLException;
    void deleteEmployee(EmployeeDTO employeeDTO) throws SQLException;
    boolean register(EmployeeDTO employeeDTO, String password, Role role) throws SQLException, UserNotFound;
    boolean login(String login,String passWord) throws NoSuchFieldException, SQLException, UserNotFound;

}
