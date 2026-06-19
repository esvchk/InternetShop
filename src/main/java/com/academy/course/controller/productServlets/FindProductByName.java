package com.academy.course.controller.productServlets;

import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;
import com.academy.course.service.validator.IdValidatorFactory;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/FindProductByName")
public class FindProductByName extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String name = ParameterConverter.getStringParameter(request,"name");
       request.setAttribute("products",productService.findProductsByName(name));
       request.getRequestDispatcher("/FindProductByName.jsp")
               .forward(request,response);
    }
}
