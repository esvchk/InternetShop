package com.academy.course.dao.customerDao;


import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;

import javax.persistence.Query;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class CustomerDAOImpl extends DAOImpl<Customer> implements CustomerDAO {
    public CustomerDAOImpl() {
        super(Customer.class);
    }

    @Override
    public void createOrder(Customer customer, Order order) throws SQLException {

        order.setCustomer(customer);

        order.setCreateDateTime(LocalDateTime.now());

        customer.getOrders().add(order);

        save(customer);
    }

    @Override
    public void deleteOrder(Customer customer, Order order) throws SQLException {

        customer.getOrders().removeIf(order1 -> order1.getId().equals(order.getId()));

        update(customer);
    }

    @Override
    public Customer getCustomerByLogin(String login) {
        Query query = getEm().createQuery("from Customer customer where customer.login=: login", Customer.class);
        query.setParameter("login", login);
        return (Customer) query.getSingleResult();
    }

    @Override
    public String getPassByLogin(String login) {
        Query query = getEm().createQuery("SELECT passWord from Customer customer where customer.login =: login");
        query.setParameter("login", login);
        return query.getSingleResult().toString();
    }

    @Override
    public List<Customer> getAllCustomers() {
        return getEm().createQuery("from Customer customer", Customer.class).getResultList();
    }


    @Override
    public Set<Order> getAllOrdersOfCustomer(Customer customer) {
        return customer.getOrders();
    }


}
