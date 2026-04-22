package com.academy.course;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Customer;
import com.academy.course.model.Operator;
import com.academy.course.model.Order;
import com.academy.course.model.Product;
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
                .products(products)
                .customers(customers)
                .operator(operator)
                .build();

        orders.add(order);
        products.add(product);
        customers.add(customer);



        DAO<Product> dao = new DAOImpl<>(Product.class);
        DAO<Order> dao1 = new DAOImpl<>(Order.class);
        DAO<Customer> dao2 = new DAOImpl<>(Customer.class);
        DAO<Operator> dao3 = new DAOImpl<>(Operator.class);
//        ProductServiceImpl productService = new ProductServiceImpl();
        dao.save(product);
        dao3.save(operator);
        dao2.save(customer);
        dao1.save(order);


//        System.out.println(productService.findAllProducts());


    }
}
