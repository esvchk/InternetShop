package com.academy.course.controller;

import com.academy.course.dto.ProductDTO;
import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Enumeration;

public class AddProduct extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Double price = Double.parseDouble(request.getParameter("price"));
        String info = request.getParameter("info");
        String manufacturer = request.getParameter("manufacturer");
        LocalDate bestBefore = LocalDate.parse(request.getParameter("bestBefore"));
        Enumeration<String> params = request.getParameterNames();


        ProductDTO productDTO = ProductDTO.builder()
                .name(name)
                .price(price)
                .info(info)
                .manufacturer(manufacturer)
                .bestBefore(bestBefore)
                .build();
        try {
            productService.addProduct(productDTO);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/register.jsp");
        dispatcher.forward(request, response);

    }
}
