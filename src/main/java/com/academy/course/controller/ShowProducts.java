package com.academy.course.controller;

import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowProducts extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products",productService.findAllProducts());
        request.getRequestDispatcher("/AllProducts.jsp")
                .forward(request,response);
    }
}
