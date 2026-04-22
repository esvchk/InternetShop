package com.academy.course;

import com.academy.course.model.Operator;
import com.academy.course.model.Order;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class App
{
    public static void main( String[] args ) throws SQLException {

        Set<Operator> operators = new HashSet<>();
        Set<Order> orders = new HashSet<>();
        Set<Product> products = new HashSet<>();

        Operator operator = Operator.builder()
                .login("Sam")
                .passWord("12345")
                .accessLevel("user")
                .build();
        operators.add(operator);


        Order order = Order.builder()
                .products(products)
                .build();

        Product product = Product.builder()
                .name("Cheese")
                .price(4.44)
                .info("Cheder")
                .manufacturer("Brest-Litovsk")
                .orders(orders)
                .bestBefore(LocalDate.of(2026,4,23))
                .build();

//
//        DAO<Product> dao = new DAOImpl<>(Product.class);
//        ProductServiceImpl productService = new ProductServiceImpl();
//        dao.save(product);
//        System.out.println(productService.findAllProducts());


    }
}
