package com.academy.course.mapper;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.CustomerShortDTO;
import com.academy.course.model.Customer;

import java.util.HashSet;
import java.util.Set;

public class CustomerMapper implements Mapper<Customer,CustomerDTO>,ShortMapper<CustomerShortDTO,Customer>{

    private final OrderMapper orderMapper;

    public CustomerMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }


    @Override
    public CustomerDTO mapToDTO(Customer entity) {
        return CustomerDTO.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .email(entity.getEmail())
                .orderDTOs(orderMapper.mapToSetDTOS(entity.getOrders()))
                .build();
    }

    @Override
    public Customer mapToEntity(CustomerDTO dto) {
        return Customer.builder()
                .orders(orderMapper.mapToSetEntities(dto.getOrderDTOs()))
                .login(dto.getLogin())
                .email(dto.getEmail())
                .build();
    }

    @Override
    public Set<Customer> mapToSetEntities(Set<CustomerDTO> dtoSet) {
        Set<Customer> list = new HashSet<>();
        for (CustomerDTO customerDTO : dtoSet){
            Customer customer = Customer.builder()
                    .login(customerDTO.getLogin())
                    .email(customerDTO.getEmail())
                    .build();
            list.add(customer);
        }
        return list;
    }

    @Override
    public Set<CustomerDTO> mapToSetDTOS(Set<Customer> entitySet) {
        Set<CustomerDTO> set = new HashSet<>();
        for (Customer customer : entitySet){
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .id(customer.getId())
                    .login(customer.getLogin())
                    .email(customer.getEmail())
                    .orderDTOs(orderMapper.mapToSetDTOS(customer.getOrders()))
                    .build();
            set.add(customerDTO);
        }
        return set;
    }

    @Override
    public CustomerShortDTO mapToShortDTO(Customer dto) {
        return CustomerShortDTO.builder()
                .id(dto.getId())
                .login(dto.getLogin())
                .email(dto.getEmail())
                .build();
    }
}
