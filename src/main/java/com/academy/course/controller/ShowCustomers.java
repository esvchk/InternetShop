package com.academy.course.controller;

import com.academy.course.service.*;
import com.academy.course.service.validator.IdValidatorFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.io.IOException;

@WebServlet("/ShowCustomers")
public class ShowCustomers extends HttpServlet {


    IdValidatorFactory idValidatorFactory = new IdValidatorFactory();
    ProductService productService = new ProductServiceImpl(idValidatorFactory);
    OrderService orderService = new OrderServiceImpl(idValidatorFactory,productService);
    private final EmployeeService employeeService = new EmployeeServiceImpl(idValidatorFactory,orderService);

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
