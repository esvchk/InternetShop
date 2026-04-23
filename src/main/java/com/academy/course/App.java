package com.academy.course;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
import com.academy.course.model.*;
import com.academy.course.utils.HibernateUtil;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class App
{
    public static void main( String[] args ) throws SQLException {

        Set<Order> orders = new HashSet<>();
        Set<Product> products = new HashSet<>();
        Set<Customer> customers = new HashSet<>();
        Set<OrderItem> orderItems = new HashSet<>();


        Product product = Product.builder()
                .name("Cheese")
                .price(4.44)
                .info("Cheder")
                .manufacturer("Brest-Litovsk")
                .bestBefore(LocalDate.of(2026,4,23))
                .build();

        Customer customer = Customer.builder()
                .login("Asfasfsa")
                .passWord("safdasfasf")
                .email("Asfasfasf")
                .payment("asfasfas")
                .orders(orders)
                .build();

        Operator operator = Operator.builder()
                .login("Sam")
                .passWord("12345")
                .accessLevel("user")
                .orders(orders)
                .build();


        Order order = Order.builder()
                .orderItems(orderItems)
                .customer(customer)
                .operator(operator)
                .build();

        OrderItem orderItem = OrderItem.builder()
                .product(product)
                .order(order)
                .quantity(4)
                .build();

        orders.add(order);
        products.add(product);
        customers.add(customer);
        orderItems.add(orderItem);




        DAO<Product> dao = new DAOImpl<>(Product.class);
        DAO<Order> dao1 = new DAOImpl<>(Order.class);
        DAO<Customer> dao2 = new DAOImpl<>(Customer.class);
        DAO<Operator> dao3 = new DAOImpl<>(Operator.class);
        DAO<OrderItem> dao4 = new DAOImpl<>(OrderItem.class);
//        ProductServiceImpl productService = new ProductServiceImpl();

        dao.save(product);
        dao2.save(customer);


//        System.out.println(productService.findAllProducts());


    }
}
