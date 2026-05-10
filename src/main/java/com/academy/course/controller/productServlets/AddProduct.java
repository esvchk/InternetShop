package com.academy.course.controller.productServlets;

import com.academy.course.dto.ProductDTO;
import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = ParameterConverter.getStringParameter(request,"name");
        Double price = ParameterConverter.getDoubleParameter(request,"price");
        String info = ParameterConverter.getStringParameter(request,"info");
        String manufacturer = ParameterConverter.getStringParameter(request,"manufacturer");
        LocalDate bestBefore = ParameterConverter.getDateParameter(request,"bestBefore");

        String context = request.getContextPath();
        ProductDTO productDTO = ProductDTO.builder()
                .name(name)
                .price(price)
                .info(info)
                .build();
        try {
            productService.addProduct(productDTO);
            response.sendRedirect(context + "/ShowProducts");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
