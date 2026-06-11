package com.academy.course.controller;

import com.academy.course.service.EmployeeService;
import com.academy.course.service.EmployeeServiceImpl;
import com.academy.course.service.IdValidatorFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@WebServlet("/ShowCustomers")
public class ShowCustomers extends HttpServlet {


    private IdValidatorFactory idValidatorFactory;
    private final EmployeeService employeeService = new EmployeeServiceImpl(idValidatorFactory);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("customers", employeeService.getAllEmployeesWithOrdersAndItems());
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }
        request.getRequestDispatcher("/AllCustomers.jsp")
                .forward(request,response);
    }
}
