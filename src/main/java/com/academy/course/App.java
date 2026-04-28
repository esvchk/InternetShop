package com.academy.course;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.model.*;
import com.academy.course.service.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class App {
    public static void main(String[] args) throws SQLException {

        Set<Order> orders = new HashSet<>();
        Set<OrderItem> orderItems = new HashSet<>();

        Product product = Product.builder()
                .name("Eggs ")
                .price(4.2)
                .info("1pcs")
                .manufacturer("Soligorsk")
                .bestBefore(LocalDate.of(2026, 4, 23))
                .build();

        Customer customer = Customer.builder()
                .login("Sam")
                .passWord("12345")
                .email("@Sam.org")
                .payment("cash")
                .orders(orders)
                .build();


        Order order = Order.builder()
                .orderItems(null)
                .customer(null)
                .build();


        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .order(null)
                .quantity(4)
                .build();


        orders.add(order);
        orderItems.add(orderItem);


        DAO<Product> dao = new DAOImpl<>(Product.class);
        OrderDAO orderDAO = new OrderDAOImpl();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        OrderService orderService = new OrderServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        System.out.println(customerService.getAllCustomers());
//        customerDAO.createOrder(customerDAO.get(1),orderDAO.get(2));
//        orderDAO.addProductToOrder(dao.get(4),orderDAO.get(2),1);


    }
}
