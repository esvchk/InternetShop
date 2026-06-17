package com.academy.course.controller;

import com.academy.course.service.*;
import com.academy.course.service.validator.IdValidatorFactory;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/LoginCustomer")
public class LoginCustomer extends HttpServlet {


    IdValidatorFactory idValidatorFactory = new IdValidatorFactory();
    ProductService productService = new ProductServiceImpl(idValidatorFactory);
    OrderService orderService = new OrderServiceImpl(idValidatorFactory,productService);
    private final EmployeeService employeeService = new EmployeeServiceImpl(idValidatorFactory,orderService);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String login = ParameterConverter.getStringParameter(request,"login");
        String passWord = ParameterConverter.getStringParameter(request, "passWord");

        String context = request.getContextPath();

        try {
            employeeService.login(login, passWord);
        } catch (NoSuchFieldException | SQLException e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(context + "/ShowProducts");
    }
}
