package com.academy.course.controller.orderServlets;

import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Order;
import com.academy.course.service.EmployeeService;
import com.academy.course.service.OrderService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/FullListOfOrders")
public class FulListOfOrders extends HttpServlet {

    private OrderService orderService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        orderService = (OrderService) context.getAttribute("orderService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("orders", orderService.getAllOrdersWithItems());
        request.getRequestDispatcher("/FullListOfOrders.jsp")
                .forward(request, response);
    }
}
