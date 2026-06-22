package com.academy.course.dao.employeeDao;


import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Employee;
import com.academy.course.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.*;

public class EmployeeDAOImpl extends DAOImpl<Employee> implements EmployeeDAO {
    private static final Logger logger = LogManager.getLogger(EmployeeDAOImpl.class);

    public EmployeeDAOImpl() {
        super(Employee.class);
    }

    @Override
    public Employee getEmployeeByLogin(String login) {
        Query query = getEm().createQuery("from Employee employee WHERE employee.login =: login", Employee.class);
        query.setParameter("login",login);
        if (!query.getResultList().isEmpty()) {
            return (Employee) query.getSingleResult();
        }
            return null;
    }

    @Override
    public Set<Employee> getAllEmployees(int offSet, int size) {
        return new HashSet<>(getEm().createQuery("FROM Employee employee ORDER BY employee.id", Employee.class)
                .setFirstResult(offSet)
                .setMaxResults(size)
                .getResultList());
    }

    @Override
    public Long countProducts() {
        return getEm().createQuery("SELECT COUNT(employee.id) from Employee employee", Long.class).getSingleResult();
    }

    @Override
    public Set<Employee> getAllEmployees() {
        return new HashSet<>(getEm().createQuery("from Employee employee", Employee.class).getResultList());
    }
}
