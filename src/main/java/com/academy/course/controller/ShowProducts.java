package com.academy.course.controller;

import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowProducts extends HttpServlet {

    ProductService productService = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("products",productService.findAllProducts());
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/productsDB");
        dispatcher.forward(req,resp);

    }
}
