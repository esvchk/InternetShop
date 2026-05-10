package com.academy.course.dao.customerDao;


import com.academy.course.dao.DAOImpl;
import com.academy.course.exception.UserNotFound;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class CustomerDAOImpl extends DAOImpl<Customer> implements CustomerDAO {
    public CustomerDAOImpl() {
        super(Customer.class);
    }

    @Override
    public void deleteOrder(Customer customer, Order order) throws SQLException {

        customer.getOrders().remove(order);

        update(customer);
    }

    @Override
    public Customer getCustomerByLogin(String login) throws UserNotFound {
        try {
            Query query = getEm().createQuery("from Customer customer where customer.login=: login", Customer.class);
            query.setParameter("login", login);
            if (!query.getResultList().isEmpty()) {
                return (Customer) query.getSingleResult();
            } else
                return null;
        } catch (NoResultException e) {
            throw new UserNotFound("login is not exist" + login);
        }

    }


    @Override
    public List<Customer> getAllCustomers() {
        return getEm().createQuery("from Customer customer", Customer.class).getResultList();
    }


    @Override
    public List<Order> getAllOrdersOfCustomer(Customer customer) {
        try {
            if (customer.getOrders().isEmpty()) {
                return Collections.emptyList();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return customer.getOrders();
    }
}
