package com.academy.course.dao.employeeDao;


import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Query;
import java.util.*;

public class EmployeeDAOImpl extends DAOImpl<Employee> implements EmployeeDAO {
    private static final Logger logger = LogManager.getLogger(EmployeeDAOImpl.class);

    public EmployeeDAOImpl() {
        super(Employee.class);
    }

    @Override
    public Employee getEmployeeByLogin(String login) {
        Query query = getEm().createQuery("from Employee employee where employee.login=: login", Employee.class);
        query.setParameter("login", login);
        if (!query.getResultList().isEmpty()) {
            return (Employee) query.getSingleResult();
        } else
            return null;
    }


    @Override
    public Set<Employee> getAllEmployees() {
        return new HashSet<>(getEm().createQuery("from Employee customer", Employee.class).getResultList());
    }
}
