package com.academy.course.controller;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.service.CustomerService;
import com.academy.course.service.CustomerServiceImpl;
import com.academy.course.service.RegistrationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class Registration extends HttpServlet {

    private final CustomerService customerService = new CustomerServiceImpl();
    private final RegistrationService registrationService = new RegistrationService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String login = request.getParameter("login");
        String passWord = request.getParameter("passWord");
        String email = request.getParameter("email");
        String payment = request.getParameter("payment");
        //конвертер для параметров

        String context = request.getContextPath();

        if (registrationService.tryToLogin(login)) {
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .login(login)
                    .email(email)
                    .payment(payment)
                    .build();
            //слой сервиса
            try {
                registrationService.register(customerDTO);
                //передать доп поля
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                customerService.createCustomer(customerDTO);
                response.sendRedirect(context + "/ShowProducts");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
