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
@WebServlet("/FindEmployeeByLogin")
public class FindEmployeeByLogin extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = ParameterConverter.getStringParameter(request,"login");
        request.setAttribute("employee",employeeService.findEmployeeByLogin(login));
        request.getRequestDispatcher("/FindEmployeeByLogin.jsp")
                .forward(request,response);
    }
}
