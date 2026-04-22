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
        ;

        Set<Operator> operators = new HashSet<>();
        Set<Order> orders = new HashSet<>();
        Set<Product> products = new HashSet<>();
        Set<Customer> customers = new HashSet<>();


        Product product = Product.builder()
                .name("Cheese")
                .price(4.44)
                .info("Cheder")
                .manufacturer("Brest-Litovsk")
                .orders(null)
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

        operators.add(operator);
        orders.add(order);
        products.add(product);
        customers.add(customer);



        DAO<Product> dao = new DAOImpl<>(Product.class);
//        ProductServiceImpl productService = new ProductServiceImpl();
        dao.save(product);
//        System.out.println(productService.findAllProducts());


    }
}
