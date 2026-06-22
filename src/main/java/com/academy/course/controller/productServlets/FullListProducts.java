package com.academy.course.controller.productServlets;

import com.academy.course.dto.ProductDTO;
import com.academy.course.paginator.PaginatedResult;
import com.academy.course.service.ProductService;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/FullListOfProducts")
public class FullListProducts extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("products", productService.getAllProducts());

        request.getRequestDispatcher("/FullListOfProducts.jsp")
                .forward(request, response);
    }
}

