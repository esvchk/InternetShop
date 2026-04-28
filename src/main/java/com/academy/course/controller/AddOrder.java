package com.academy.course.controller;

import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.service.OrderService;
import com.academy.course.service.OrderServiceImpl;
import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddOrder extends HttpServlet {
    private final OrderService orderService = new OrderServiceImpl();

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//            String name = request.getParameter("name");
//            Double price = Double.valueOf(request.getParameter("price"));
//            String info = request.getParameter("info");
//            String manufacturer = request.getParameter("manufacturer");
//            LocalDate bestBefore = LocalDate.parse(request.getParameter("bestBefore"));
//
//            String context = request.getContextPath();
//
//        OrderDTO orderDTO = OrderDTO.builder()
//                .customer()
//                .isBought()
//                .dateTimeOfCreation()
//                .dateTimeOfPurchasing()
//                .build();
//            try {
//                productService.addProduct(productDTO);
//                response.sendRedirect(context + "/ShowProducts");
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//
//
//
//    }
}
