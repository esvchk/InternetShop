package com.academy.course;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Product;
import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import java.sql.SQLException;

public class App
{
    public static void main( String[] args ) throws SQLException {
        Product product = Product.builder()
                .name("Milk")
                .price(3.32)
                .info("3.2%")
                .build();

        DAO<Product> dao = new DAOImpl<>(Product.class);
        ProductServiceImpl productService = new ProductServiceImpl();
        dao.save(product);
//        System.out.println(productService.findAllProducts());
    }
}
