package com.academy.course.controller.productServlets;

import com.academy.course.service.ProductService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/ShowProducts")
public class ShowProducts extends HttpServlet {


    private ProductService productService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("products",productService.getAllProducts());
        request.getRequestDispatcher("/AllProducts.jsp")
                .forward(request,response);
    }


}
