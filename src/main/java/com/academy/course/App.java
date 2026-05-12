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

        List<Order> orders = new ArrayList<>();
        List<Item> items = new ArrayList<>();


        Product product = Product.builder()
                .name("Cheese")
                .price(2.45)
                .info("45%")
                .isAvailable(true)
                .build();

        Customer customer = Customer.builder()
                .login("Sam")
                .passWord("123")
                .email("@Sam.org")
                .orders(null)
                .build();


        Order order = Order.builder()
                .items(null)
                .customer(customer)
                .build();

        Item item = Item.builder()
                .productQuantity(null)
                .product(product)
                .order(order)
                .build();

        items.add(item);
        orders.add(order);


        CustomerMapper customerMapper = new CustomerMapper();
        ProductMapper productMapper = new ProductMapper();
        ItemMapper itemMapper = new ItemMapper(productMapper);
        OrderMapper orderMapper = new OrderMapper(itemMapper, customerMapper);
        DAO<Product> dao = new DAOImpl<>(Product.class);
        OrderDAO orderDAO = new OrderDAOImpl();
        CustomerDAO customerDAO = new CustomerDAOImpl();
        OrderService orderService = new OrderServiceImpl();
        ProductServiceImpl productService = new ProductServiceImpl();
        CustomerService customerService = new CustomerServiceImpl();
        CustomerWithOrdersMapper customerWithOrdersMapper = new CustomerWithOrdersMapper(customerMapper,orderMapper);


//        dao.save(product);
//        productService.deleteProduct(productMapper.mapToDTO(dao.get(9)));
//        orderDAO.addProductToOrder(dao.get(1),orderDAO.get(3),1);
//        customerDAO.save(customer);
//        orderDAO.deleteProductFromOrder(dao.get(9),orderDAO.get(2));
//        customerDAO.save(customer);
//        orderDAO.createEmptyOrder(customerDAO.get(5));


//        orderDAO.save(order);
//        customerDAO.createOrder(customerDAO.get(2),orderDAO.get(3));
//        customerDAO.deleteOrder(customerDAO.get(1),orderDAO.get(12));
//        customerService.buyOrder(customerMapper.mapToDTO(customerDAO.get(2)),orderMapper.mapToDTO(orderDAO.get(3)));
//        customerDAO.delete(customerDAO.get(7));


    }
}
