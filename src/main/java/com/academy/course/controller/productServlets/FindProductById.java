package com.academy.course.controller.productServlets;

import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;
import com.academy.course.service.validator.IdValidatorFactory;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
@WebServlet("/FindProductById")
public class FindProductById extends HttpServlet {
    IdValidatorFactory idValidatorFactory = new IdValidatorFactory();
    private final ProductService productService = new ProductServiceImpl(idValidatorFactory);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        try {
            request.setAttribute("product", productService.getProductById(id));
            request.getRequestDispatcher("/FindProductById.jsp")
                    .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
