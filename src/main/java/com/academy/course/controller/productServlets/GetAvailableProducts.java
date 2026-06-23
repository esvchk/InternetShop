package com.academy.course.controller.productServlets;

import com.academy.course.service.ProductService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/GetAvailableProducts")
public class GetAvailableProducts extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("availableProducts",productService.getAvailableProducts());
        request.getRequestDispatcher("/OrderPage.jsp")
                .forward(request, response);
    }


}
