package com.academy.course;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
import com.academy.course.dao.ItemDAO;
import com.academy.course.dao.ItemDAOImpl;
import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.exception.UserNotFound;
import com.academy.course.mapper.*;
import com.academy.course.model.*;
import com.academy.course.service.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class App {
    public static void main(String[] args) throws SQLException, UserNotFound, NoSuchFieldException {

        Set<Order> orders = new HashSet<>();
        Set<Item> items = new HashSet<>();


        Product product = Product.builder()
                .name("Cheese")
                .price(2.45)
                .info("45%")
                .isAvailable(true)
                .build();

        Customer customer = Customer.builder()
                .login("Grigoriy")
                .email("@Gosha.org")
                .orders(orders)
                .build();


        Order order = Order.builder()
                .items(items)
                .customer(customer)
                .build();

        Item item = Item.builder()
                .productQuantity(1)
                .product(product)
                .order(order)
                .build();

        orders.add(order);
        items.add(item);


        ProductMapper productMapper = new ProductMapper();
        ItemMapper itemMapper = new ItemMapper(productMapper);
        OrderMapper orderMapper = new OrderMapper(itemMapper);
        CustomerMapper customerMapper = new CustomerMapper(orderMapper);
        DAO<Product> dao = new DAOImpl<>(Product.class);
        ItemService itemService = new ItemServiceImpl();
        OrderDAO orderDAO = new OrderDAOImpl();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        OrderService orderService = new OrderServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        ItemDAO itemDAO = new ItemDAOImpl();
        CustomerWithOrdersMapper customerWithOrdersMapper = new CustomerWithOrdersMapper(customerMapper, orderMapper);

        customerService.login("Sam","123");

    }
}
