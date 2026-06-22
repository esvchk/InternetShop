package com.academy.course.controller.employeeServlets;

import com.academy.course.dto.EmployeeDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.service.EmployeeService;
import com.academy.course.service.ProductService;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/SetLimit")
public class SetLimitOnProduct extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        try {
            ProductDTO productDTO = productService.getProductById(id);
            request.setAttribute("product", productDTO);
            request.getRequestDispatcher("/SetLimitForm.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        Integer limit = ParameterConverter.getIntegerParameter(request,"limit");

        String context = request.getContextPath();

        try {
            productService.setProductLimit(id,limit);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        response.sendRedirect(context + "/ShowProducts");

    }
}
