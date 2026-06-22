package com.academy.course.service;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.OrderDTO;

import com.academy.course.model.Employee;
import com.academy.course.paginator.PaginatedResult;
import com.academy.course.utils.Role;

import javax.xml.bind.ValidationException;
import java.sql.SQLException;
import java.util.Set;

public interface EmployeeService {

    void deleteOrderOfEmployee(EmployeeDTO employeeDTO, OrderDTO orderDTO) throws SQLException;
    Set<EmployeeDTO> getAllEmployees() throws ValidationException;
    PaginatedResult<EmployeeDTO> getAllEmployees(int offset, int size);
    EmployeeDTO findEmployeeById(Integer id) throws SQLException;
    EmployeeDTO findEmployeeByLogin(String login);
    void addNewOrderToEmployee(EmployeeDTO employeeDTO) throws SQLException;
    boolean registerEmployee(String login, String pass, Role role) throws SQLException;
    void updateEmployee(Integer oldValueId, EmployeeDTO newValue) throws SQLException;
    void deleteEmployee(Integer employeeId) throws SQLException;
    boolean login(String login,String passWord) throws NoSuchFieldException, SQLException;
    Double getTotalAmountOfOrders() throws SQLException;

}
