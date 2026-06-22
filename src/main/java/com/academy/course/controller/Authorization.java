package com.academy.course.controller;

import com.academy.course.service.*;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/Authorization")
public class Authorization extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

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
