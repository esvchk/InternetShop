package com.academy.course.controller;

import com.academy.course.service.CustomerService;
import com.academy.course.service.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowCustomers extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("customers",customerService.getAllCustomers());
        request.getRequestDispatcher("/AllCustomers.jsp")
                .forward(request,response);
    }
}
