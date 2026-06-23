package com.academy.course.controller.employeeServlets;

import com.academy.course.service.EmployeeService;
import com.academy.course.utils.ParameterConverter;
import com.academy.course.utils.Role;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/RegisterEmployee")
public class Registration extends HttpServlet {

    private EmployeeService employeeService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String passWord = ParameterConverter.getStringParameter(request,"passWord");
        String login = ParameterConverter.getStringParameter(request,"login");
        String roleParam = ParameterConverter.getStringParameter(request,"role");
        Role role = Role.valueOf(roleParam.toUpperCase());
        String context = request.getContextPath();
        HttpSession session  = request.getSession();
        session.setAttribute("login",login);
        try {
            employeeService.registerEmployee(login,passWord,role);
            response.sendRedirect(context + "/ShowProducts");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
