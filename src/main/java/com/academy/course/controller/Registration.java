package com.academy.course.controller;

import com.academy.course.service.CustomerService;
import com.academy.course.service.CustomerServiceImpl;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Registration extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = ParameterConverter.getStringParameter(request,"login");
        String passWord = ParameterConverter.getStringParameter(request,"passWord");

        String context = request.getContextPath();

            try {
                customerService.register(login,passWord);
                response.sendRedirect(context + "/ShowCustomers");
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }

    }
}
