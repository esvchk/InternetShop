package com.academy.course.controller;

import com.academy.course.exception.UserNotFound;
import com.academy.course.service.CustomerService;
import com.academy.course.service.CustomerServiceImpl;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/LoginCustomer")
public class LoginCustomer extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String login = session.getAttribute("login").toString();
        String passWord = ParameterConverter.getStringParameter(request, "passWord");

        String context = request.getContextPath();
        try {
            customerService.login(login, passWord);
        } catch (NoSuchFieldException | SQLException | UserNotFound e) {
            throw new RuntimeException(e);
        }
        response.sendRedirect(context + "/Cart.jsp");
    }
}
