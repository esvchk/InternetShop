package com.academy.course.controller;

import com.academy.course.service.EmployeeService;
import com.academy.course.service.EmployeeServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.sql.SQLException;

@WebServlet("/RegisterCustomer")
public class Registration extends HttpServlet {

    private EmployeeService employeeService;

//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String login = request.getParameter("login");
//        String passWord = request.getParameter("passWord");
//
//        String context = request.getContextPath();
//        try {
//            employeeService.register(login, passWord);
//        } catch (SQLException | UserNotFound e) {
//            throw new RuntimeException(e);
//        }
//        response.sendRedirect(context + "/ShowProducts");
//    }
}
