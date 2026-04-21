package com.academy.course.controller;

import com.academy.course.dto.ProductDTO;
import com.academy.course.dto.UserDTO;
import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class UpdateProduct extends HttpServlet {

    private final ProductService productService = new ProductServiceImpl();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = Integer.parseInt(request.getParameter("id"));
        try {
            ProductDTO productDTO = productService.findProductById(id);
            request.setAttribute("product",productDTO);
            request.getRequestDispatcher("UpdateProduct.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        Double price = Double.valueOf(request.getParameter("price"));
        String info = request.getParameter("info");
        String manufacturer = request.getParameter("manufacturer");
        LocalDate bestBefore = LocalDate.parse(request.getParameter("bestBefore"));

        ProductDTO productDTO = ProductDTO.builder()
                .name(name)
                .price(price)
                .info(info)
                .manufacturer(manufacturer)
                .bestBefore(bestBefore)
                .build();
        try {
            productService.updateProduct(productDTO);
            response.sendRedirect("/AllProducts.jsp");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
