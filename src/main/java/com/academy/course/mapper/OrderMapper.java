package com.academy.course.mapper;

import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Order;


import java.util.ArrayList;
import java.util.List;

public class OrderMapper implements Mapper<Order, OrderDTO>{
    private final ItemMapper itemMapper = new ItemMapper();
    private final CustomerMapper customerMapper = new CustomerMapper();



    @Override
    public OrderDTO mapToDTO(Order entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .itemsDTO(itemMapper.mapToListDTOS(entity.getItems()))
                .customerDTO(customerMapper.mapToDTO(entity.getCustomer()))
                .isBought(null)
                .build();
    }

    @Override
    public Order mapToEntity(OrderDTO dto) {
        return Order.builder()
                .items(itemMapper.mapToListEntities(dto.getItemsDTO()))
                .customer(customerMapper.mapToEntity(dto.getCustomerDTO()))
                .build();
    }

    @Override
    public List<Order> mapToListEntities(List<OrderDTO> dtoList) {
        List<Order> orders = new ArrayList<>();
        for (OrderDTO orderDTO : dtoList){
            Order order = Order.builder()
                    .items(itemMapper.mapToListEntities(orderDTO.getItemsDTO()))
                    .customer(customerMapper.mapToEntity(orderDTO.getCustomerDTO()))
                    .build();
            orders.add(order);
        }
        return orders;
    }

    @Override
    public List<OrderDTO> mapToListDTOS(List<Order> entityList) {
        List<OrderDTO> orderDTOS = new ArrayList<>();
        for (Order order : entityList){
            OrderDTO orderDTO = OrderDTO.builder()
                    .id(order.getId())
                    .itemsDTO(itemMapper.mapToListDTOS(order.getItems()))
                    .customerDTO(customerMapper.mapToDTO(order.getCustomer()))
                    .isBought(null)
                    .build();
            orderDTOS.add(orderDTO);
        }
        return orderDTOS;
    }
}
