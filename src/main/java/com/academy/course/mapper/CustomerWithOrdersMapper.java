package com.academy.course.mapper;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.model.Customer;

import java.util.stream.Collectors;

public class CustomerWithOrdersMapper {
    private final CustomerMapper customerMapper;
    private final OrderMapper orderMapper;


    public CustomerWithOrdersMapper(CustomerMapper customerMapper, OrderMapper orderMapper) {
        this.customerMapper = customerMapper;
        this.orderMapper = orderMapper;
    }

    public CustomerDTO toDTOWithOrders(Customer customer){
        CustomerDTO customerDTO = customerMapper.mapToDTO(customer);
        customerDTO.setOrderShortDTOS(customer.getOrders().stream()
                .map(orderMapper::mapToShortDTO)
                .collect(Collectors.toList()));
        return customerDTO;
    }

}
