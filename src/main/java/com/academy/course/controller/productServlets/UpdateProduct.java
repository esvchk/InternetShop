package com.academy.course.controller.productServlets;

import com.academy.course.dto.ProductDTO;
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
import java.sql.SQLException;

@WebServlet("/UpdateProduct")
public class UpdateProduct extends HttpServlet {


    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        try {
            ProductDTO productDTO = productService.getProductById(id) ;
                request.setAttribute("product", productDTO);
                request.getRequestDispatcher("/UpdateProduct.jsp")
                        .forward(request, response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        String name = ParameterConverter.getStringParameter(request,"name");
        Double price = ParameterConverter.getDoubleParameter(request,"price");
        String info = ParameterConverter.getStringParameter(request,"info");
        Boolean isAvailable = ParameterConverter.getBooleanParameter(request,"isAvailable");
        Integer limit = ParameterConverter.getIntegerParameter(request,"limit");

        String context = request.getContextPath();

        ProductDTO productDTO = ProductDTO.builder()
                .id(id)
                .name(name)
                .price(price)
                .info(info)
                .isAvailable(isAvailable)
                .productLimit(limit)
                .build();
        try {
            productService.updateProduct(id,productDTO);
            response.sendRedirect(context + "/ShowProducts");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
