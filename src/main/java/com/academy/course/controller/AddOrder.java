package com.academy.course.controller;

import com.academy.course.dto.ProductDTO;
import com.academy.course.service.OrderService;
import com.academy.course.service.OrderServiceImpl;
import com.academy.course.service.ProductService;
import com.academy.course.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

public class AddOrder extends HttpServlet {
    private final OrderService orderService = new OrderServiceImpl();

}
