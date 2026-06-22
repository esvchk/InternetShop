package com.academy.course.controller.productServlets;

import com.academy.course.dto.ProductDTO;
import com.academy.course.paginator.PaginatedResult;
import com.academy.course.service.ProductService;
import com.academy.course.utils.Constants;
import com.academy.course.utils.ParameterConverter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

@WebServlet("/ShowProducts")
public class ShowProducts extends HttpServlet {


    private ProductService productService;

    @Override
    public void init() {
        ServletContext context = getServletContext();
        productService = (ProductService) context.getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int currentPage = 1;
        int pageSize = 5;
        HttpSession session = request.getSession();
        String reqValue = request.getParameter("pageSize");
        if (reqValue != null) {
            pageSize = ParameterConverter.getIntegerParameter(request,"pageSize");
            session.setAttribute("pageSize",pageSize);
        }
        if (request.getParameter("currentPage") != null) {
            currentPage = ParameterConverter.getIntegerParameter(request, "currentPage");
        }

            PaginatedResult<ProductDTO> paginatedResult = productService.getPaginatedListOfProducts(currentPage,pageSize);
            Set<ProductDTO> products = paginatedResult.getEntities();
            request.setAttribute("products", products);
            request.setAttribute("pageSize", paginatedResult.getPageSize());
            request.setAttribute("currentPage", paginatedResult.getCurrentPage());
            request.setAttribute("lastPage", paginatedResult.getLastPage());

            request.getRequestDispatcher("/AllProducts.jsp")
                    .forward(request, response);
        }

    }
