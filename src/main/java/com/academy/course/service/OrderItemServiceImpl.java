package com.academy.course.service;

import com.academy.course.dto.OrderItemDTO;

import com.academy.course.model.OrderItem;
import com.academy.course.utils.ObjectMapper;

public class OrderItemServiceImpl implements ObjectMapper<OrderItem,OrderItemDTO> {

    @Override
    public OrderItemDTO mapToDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .product(orderItem.getProduct())
                .build();
    }

    @Override
    public OrderItem mapToEntity(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        return OrderItem.builder()
                .product(orderItemDTO.getProduct())
                .order(orderItem.getOrder())
                .quantity(orderItem.getQuantity())
                .build();
    }
}
