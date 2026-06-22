package com.academy.course.controller.employeeServlets;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.paginator.PaginatedResult;
import com.academy.course.service.*;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/ShowEmployees")
public class ShowEmployees extends HttpServlet {

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        employeeService = (EmployeeService) context.getAttribute("employeeService");
    }

    private EmployeeService employeeService ;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        int pageSize = 5;
        String sizeValue = request.getParameter("pageSize");
        if (sizeValue != null) {
            pageSize = ParameterConverter.getIntegerParameter(request,"pageSize");
        }
        if (request.getParameter("currentPage") != null) {
            currentPage = ParameterConverter.getIntegerParameter(request, "currentPage");
        }
        PaginatedResult<EmployeeDTO> paginatedResult = employeeService.getAllEmployees(currentPage,pageSize);
        Set<EmployeeDTO> employees = paginatedResult.getEntities();
        request.setAttribute("employees", employees);
        request.setAttribute("pageSize", paginatedResult.getPageSize());
        request.setAttribute("currentPage", paginatedResult.getCurrentPage());
        request.setAttribute("lastPage", paginatedResult.getLastPage());

        request.getRequestDispatcher("/AllEmployees.jsp")
                .forward(request, response);
    }
}
