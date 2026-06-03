package com.academy.course.dao.customerDao;


import com.academy.course.dao.DAOImpl;
import com.academy.course.exception.UserNotFound;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.*;

public class CustomerDAOImpl extends DAOImpl<Customer> implements CustomerDAO {
    private static final Logger logger = LogManager.getLogger(CustomerDAOImpl.class);

    public CustomerDAOImpl() {
        super(Customer.class);
    }



    @Override
    public void deleteOrderOfCustomer(Customer customer, Order order) throws SQLException {
        customer.getOrders().removeIf(order1 -> order1.getId().equals(order.getId()));
        update(customer);

    }

    @Override
    public Customer getCustomerByLogin(String login) {
        Query query = getEm().createQuery("from Customer customer where customer.login=: login", Customer.class);
        query.setParameter("login", login);
        if (!query.getResultList().isEmpty()) {
            return (Customer) query.getSingleResult();
        } else
            return null;
    }


    @Override
    public Set<Customer> getAllCustomers() {
        return new HashSet<>(getEm().createQuery("from Customer customer", Customer.class).getResultList());
    }


    @Override
    public Set<Order> getAllOrdersOfCustomer(Customer customer) {
        if (customer.getOrders() == null) {
            return Collections.emptySet();
        }
        return customer.getOrders();
    }
}
