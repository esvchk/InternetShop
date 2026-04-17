package com.academy.course;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Product;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
        Product product = Product.builder()
                .name("Milk")
                .price(2.94)
                .info("3.2%")
                .build();

        DAO<Product> dao = new DAOImpl<>(Product.class);
        dao.save(product);
    }
}
