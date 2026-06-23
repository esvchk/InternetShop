package com.academy.course.controller.itemServlets;

import com.academy.course.service.ItemService;
import com.academy.course.service.OrderService;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/ItemsFromOrder")
public class ItemsFromOrder extends HttpServlet {

    private ItemService itemService;


    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        itemService = (ItemService) context.getAttribute("itemService");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Integer orderId = ParameterConverter.getIntegerParameter(request,"orderId");

        try {
            request.setAttribute("items",itemService.getAllItemsFromOrder(orderId));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        request.getRequestDispatcher("/OrderPage.jsp")
                .forward(request,response);

    }
}
