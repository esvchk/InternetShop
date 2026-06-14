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
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet {
    IdValidatorFactory idValidatorFactory = new IdValidatorFactory();
    private final ProductService productService = new ProductServiceImpl(idValidatorFactory);

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        try {
            productService.deleteProduct(productService.getProductById(id));
            request.getServletContext().getRequestDispatcher("/ShowProducts")
                    .include(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
