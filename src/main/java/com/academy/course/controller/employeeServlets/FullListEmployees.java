package com.academy.course.controller.employeeServlets;

import com.academy.course.service.EmployeeService;
import com.academy.course.service.ProductService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@WebServlet("/FullListOfEmployees")
public class FullListEmployees extends HttpServlet {
    private EmployeeService employeeService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            request.setAttribute("employees", employeeService.getAllEmployees());
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/FullListOfEmployees.jsp")
                .forward(request, response);
    }
}
