package com.academy.course.service;

import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.OrderProductsDTO;
import com.academy.course.dto.ProductDTO;



import java.util.HashSet;

import java.util.Set;

public class TablesServiceImpl implements TablesService {

    private final ProductService productService;
    private final EmployeeService employeeService;

    public TablesServiceImpl(ProductService productService, EmployeeService employeeService) {
        this.productService = productService;
        this.employeeService = employeeService;
    }

    @Override
    public OrderProductsDTO getPairedEntity(String login) {
        Set<ProductDTO> products = productService.getAvailableProducts();
        OrderDTO order = employeeService.getCurrentOrderOfEmployee(login);
        return OrderProductsDTO.builder()
                .products(products)
                .orderDTO(order)
                .build();
    }
}
