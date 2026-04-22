package com.academy.course.controller.productServlets;

import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class FindProductById extends HttpServlet {
    private final ProductService productService = new ProductServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            request.setAttribute("product", productService.findProductById(id));
            request.getRequestDispatcher("/FindById.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
