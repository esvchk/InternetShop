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
        PaginatedResult<ProductDTO> paginatedResult = productService.getPaginatedListOfProducts(1, 5);
        Set<ProductDTO> products = paginatedResult.getEntities();
        request.setAttribute("products", products);
        request.setAttribute("currentPage",paginatedResult.getCurrentPage());
        request.setAttribute("pageSize", paginatedResult.getPageSize());
        request.setAttribute("listSize", paginatedResult.getTotalSize());
        request.setAttribute("lastPage", paginatedResult.getLastPage());

        request.getRequestDispatcher("/AllProducts.jsp")
                .forward(request, response);
    }


}
