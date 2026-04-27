package com.academy.course.dao.customerDao;


import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Customer;
import com.academy.course.model.Order;


import java.sql.SQLException;

public class CustomerDAOImpl extends DAOImpl<Customer> implements CustomerDAO {
    public CustomerDAOImpl() {
        super(Customer.class);
    }

    @Override
    public void createOrder(Customer customer,Order order) throws SQLException {
        order.setCustomer(customer);

        customer.getOrders().add(order);

        update(customer);
    }





}
