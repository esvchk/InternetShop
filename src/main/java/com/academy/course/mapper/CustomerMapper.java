package com.academy.course.mapper;

import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.CustomerShortDTO;
import com.academy.course.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper implements Mapper<Customer,CustomerDTO>,ShortMapper<CustomerShortDTO,Customer>{

    @Override
    public CustomerDTO mapToDTO(Customer entity) {
        return CustomerDTO.builder()
                .id(entity.getId())
                .login(entity.getLogin())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public Customer mapToEntity(CustomerDTO dto) {
        return Customer.builder()
                .login(dto.getLogin())
                .passWord(null)
                .email(dto.getEmail())
                .build();
    }

    @Override
    public List<Customer> mapToListEntities(List<CustomerDTO> dtoList) {
        List<Customer> list = new ArrayList<>();
        for (CustomerDTO customerDTO : dtoList){
            Customer customer = Customer.builder()
                    .login(customerDTO.getLogin())
                    .email(customerDTO.getEmail())
                    .build();
            list.add(customer);
        }
        return list;
    }

    @Override
    public List<CustomerDTO> mapToListDTOS(List<Customer> entityList) {
        List<CustomerDTO> list = new ArrayList<>();
        for (Customer customer : entityList){
            CustomerDTO customerDTO = CustomerDTO.builder()
                    .id(customer.getId())
                    .login(customer.getLogin())
                    .email(customer.getEmail())
                    .build();
            list.add(customerDTO);
        }
        return list;
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
