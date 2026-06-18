package com.academy.course.controller;

import com.academy.course.service.*;
import com.academy.course.service.validator.IdValidatorFactory;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/FindOrderById")
public class FindOrderById extends HttpServlet {

    private ItemService itemService;
    private ProductService productService;
    private OrderService orderService ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        try {
            request.setAttribute("order",orderService.findOrderById(id));
            request.getRequestDispatcher("FindOrderById.jsp")
                    .forward(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
