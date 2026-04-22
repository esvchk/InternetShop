package com.academy.course.controller;

import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class DeleteProduct extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            productService.deleteProduct(id);
            request.getServletContext().getRequestDispatcher("/ShowProducts")
                    .include(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
