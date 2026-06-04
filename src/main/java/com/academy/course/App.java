package com.academy.course;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
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
    public static void main(String[] args) throws SQLException, UserNotFound {

        Set<Order> orders = new HashSet<>();
        Set<Item> items = new HashSet<>();


        Product product = Product.builder()
                .name("Cheese")
                .price(2.45)
                .info("45%")
                .isAvailable(true)
                .build();

        Customer customer = Customer.builder()
                .login("Bob")
                .passWord("123")
                .email("@Bob.org")
                .orders(orders)
                .build();


        Order order = Order.builder()
                .items(items)
                .customer(customer)
                .build();

        Item item = Item.builder()
                .product(product)
                .order(order)
                .build();

        items.add(item);
        orders.add(order);



        ProductMapper productMapper = new ProductMapper();
        ItemMapper itemMapper = new ItemMapper(productMapper);
        OrderMapper orderMapper = new OrderMapper(itemMapper);
        CustomerMapper customerMapper = new CustomerMapper(orderMapper);
        DAO<Product> dao = new DAOImpl<>(Product.class);
        OrderDAO orderDAO = new OrderDAOImpl();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        OrderService orderService = new OrderServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        CustomerWithOrdersMapper customerWithOrdersMapper = new CustomerWithOrdersMapper(customerMapper,orderMapper);


//        dao.save(product);
//        System.out.println(productService.findAllProducts());
//        productService.deleteProduct(productMapper.mapToDTO(dao.get(9)));
//        orderDAO.addProductToOrder(dao.get(1),orderDAO.get(3),1);
//        customerDAO.save(customer);
//        orderDAO.deleteProductFromOrder(dao.get(9),orderDAO.get(2));
        customerService.buyOrder(orderMapper.mapToDTO(orderDAO.get(3)));
//        System.out.println(customerDAO.getAllCustomers());
//        System.out.println(customerMapper.mapToDTO(customerDAO.get(2)));

//        System.out.println(customerService.getAllOrdersOfCustomer(customerMapper.mapToDTO(customerDAO.get(2))));

//        orderDAO.delete(orderDAO.get(10));
//        orderDAO.save(order);
//        customerDAO.createOrder(customerDAO.get(2),orderDAO.get(3));
//        customerDAO.deleteOrderOfCustomer(customerDAO.get(2),orderDAO.get(4));
//        customerService.buyOrder(customerMapper.mapToDTO(customerDAO.get(2)),orderMapper.mapToDTO(orderDAO.get(3)));
//        customerDAO.delete(customerDAO.get(7));


    }
}
