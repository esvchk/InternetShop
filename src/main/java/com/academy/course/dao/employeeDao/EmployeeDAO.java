package com.academy.course.dao.employeeDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Employee;

import java.util.Set;

public interface EmployeeDAO extends DAO<Employee> {
    Employee getEmployeeByLogin(String login);
    Set<Employee> getAllEmployees();

}
