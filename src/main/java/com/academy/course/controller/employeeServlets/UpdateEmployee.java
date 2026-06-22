package com.academy.course.controller.employeeServlets;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.service.EmployeeService;
import com.academy.course.utils.ParameterConverter;
import com.academy.course.utils.Role;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/UpdateEmployee")

public class UpdateEmployee extends HttpServlet {
    private EmployeeService employeeService;
    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        try {
            EmployeeDTO employeeDTO = employeeService.findEmployeeById(id) ;
            request.setAttribute("employee", employeeDTO);
            request.getRequestDispatcher("/UpdateEmployee.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        String login = ParameterConverter.getStringParameter(request,"login");
        String roleParam = ParameterConverter.getStringParameter(request,"role");

        Role role = Role.valueOf(roleParam.toUpperCase());

        String context = request.getContextPath();

        EmployeeDTO employeeDTO = EmployeeDTO.builder()
                .id(id)
                .login(login)
                .role(role)
                .build();
        try {
            employeeService.updateEmployee(id,employeeDTO);
            response.sendRedirect(context + "/ShowEmployees");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
