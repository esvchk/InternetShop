package com.academy.course.controller;

import com.academy.course.service.AuthorizationService;
import com.academy.course.service.CustomerService;
import com.academy.course.service.CustomerServiceImpl;
import com.academy.course.service.PasswordHasher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCustomer extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();
    private final AuthorizationService authorizationService = new AuthorizationService();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String passWord = request.getParameter("passWord");

        String context = request.getContextPath();

        try {
            if (authorizationService.login(login,passWord)) {
                response.sendRedirect(context + "/CustomerPage.jsp");
            } else
                request.getServletContext().getRequestDispatcher("/CustomerPage.jsp")
                        .include(request,response);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        }


    }
}
