package com.academy.course.controller.orderServlets;

import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.model.Order;
import com.academy.course.model.Product;
import com.academy.course.service.TablesService;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/GetCurrentOrder")
public class GetCurrentOrder extends HttpServlet {

    private TablesService tablesService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
       tablesService = (TablesService) context.getAttribute("tablesService");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, IOException {
        HttpSession session = request.getSession(false);
        String login = (String) session.getAttribute("login");
        request.setAttribute("productsAndOrder",tablesService.getPairedEntity(login));

        request.getRequestDispatcher("/OrderPage.jsp")
                .forward(request, response);

    }

}
