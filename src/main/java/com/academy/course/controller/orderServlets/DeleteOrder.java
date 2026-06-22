package com.academy.course.controller.orderServlets;

import com.academy.course.service.OrderService;
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

@WebServlet("/DeleteOrder")
public class DeleteOrder extends HttpServlet {
    private OrderService orderService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        orderService = (OrderService) context.getAttribute("orderService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = ParameterConverter.getIntegerParameter(request,"id");
        try {
            orderService.deleteOrder(id);
            request.getServletContext().getRequestDispatcher("/FullListOfOrders")
                    .include(request,response);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
