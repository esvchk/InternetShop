package com.academy.course.controller;

import com.academy.course.service.OrderService;
import com.academy.course.service.OrderServiceImpl;
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
@WebServlet("/AddProductInOrder")
public class AddProductInOrder extends HttpServlet {

    private final OrderService orderService = new OrderServiceImpl();
    private final ProductService productService = new ProductServiceImpl();


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer productId = ParameterConverter.getIntegerParameter(request,"id");
        Integer orderId = ParameterConverter.getIntegerParameter(request,"id");
        Integer quantity = ParameterConverter.getIntegerParameter(request,"quantity");
        try {
            orderService.addProductToOrder(productService.findProductById(productId),
                    orderService.findOrderById(orderId),quantity);
            request.getServletContext().getRequestDispatcher("/ShowProducts")
                    .include(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
