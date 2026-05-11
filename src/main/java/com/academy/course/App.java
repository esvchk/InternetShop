package com.academy.course;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.exception.UserNotFound;
import com.academy.course.model.*;
import com.academy.course.service.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws SQLException, UserNotFound {

        List<Order> orders = new ArrayList<>();
        List<Item> items = new ArrayList<>();


        Product product = Product.builder()
                .name("Milk")
                .price(3.5)
                .info("3.2%")
                .isAvailable(true)
                .build();

        Customer customer = Customer.builder()
                .login("Max")
                .passWord("123")
                .email("@Max.org")
                .orders(orders)
                .build();


        Order order = Order.builder()
                .items(items)
                .customer(customer)
                .build();

        Item item = Item.builder()
                .productQuantity(2)
                .product(product)
                .order(order)
                .build();



        orders.add(order);


        DAO<Product> dao = new DAOImpl<>(Product.class);
        OrderDAO orderDAO = new OrderDAOImpl();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        OrderService orderService = new OrderServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();

        System.out.println(productService.findAllProducts());

//        customerDAO.save(customer);
//        dao.save(product);



    }
}
