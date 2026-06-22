package com.academy.course.controller.employeeServlets;

import com.academy.course.service.EmployeeService;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/FindEmployeeById")
public class FindEmployeeById extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        try {
            request.setAttribute("employee",employeeService.findEmployeeById(id));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/FindEmployeeById.jsp")
                .forward(request,response);
    }
}
