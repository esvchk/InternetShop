package com.academy.course.service;

import com.academy.course.dto.OrderItemDTO;

import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;

public class OrderItemServiceImpl implements OrderItemService{

    @Override
    public OrderItemDTO mapToOrderItemDTO(OrderItem orderItem) {
        return OrderItemDTO.builder()
                .id(orderItem.getId())
                .product(orderItem.getProduct())
                .build();
    }

    @Override
    public OrderItem mapToOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        return OrderItem.builder()
                .product(orderItemDTO.getProduct())
                .order(orderItem.getOrder())
                .quantity(orderItem.getQuantity())
                .build();
    }


}
