package com.academy.course.mapper;

import com.academy.course.dto.OrderDTO;
import com.academy.course.model.Order;

import java.util.HashSet;
import java.util.Set;

public class OrderMapper implements Mapper<Order, OrderDTO>{

    private final ItemMapper itemMapper;

    public OrderMapper(ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }


    @Override
    public OrderDTO mapToDTO(Order entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .itemsDTO(itemMapper.mapToSetDTOS(entity.getItems()))
                .isBought(entity.getIsBought())
                .paymentData(entity.getPaymentData())
                .totalCost(entity.getTotalCost())
                .build();
    }

    @Override
    public Order mapToEntity(OrderDTO dto) {
        return Order.builder()
                .items(itemMapper.mapToSetEntities(dto.getItemsDTO()))
                .isBought(dto.getIsBought())
                .totalCost(dto.getTotalCost())
                .build();
    }

    @Override
    public Set<Order> mapToSetEntities(Set<OrderDTO> dtoSet) {
        Set<Order> set = new HashSet<>();
        for (OrderDTO orderDTO : dtoSet) {
            Order order = Order.builder()
                    .totalCost(orderDTO.getTotalCost())
                    .paymentData(orderDTO.getPaymentData())
                    .items(itemMapper.mapToSetEntities(orderDTO.getItemsDTO()))
                    .isBought(orderDTO.getIsBought())
                    .build();
            set.add(order);
        }
        return set;
    }

    @Override
    public Set<OrderDTO> mapToSetDTOS(Set<Order> entitySet) {
        Set<OrderDTO> set = new HashSet<>();
        for (Order order : entitySet) {
            OrderDTO orderDTO = OrderDTO.builder()
                    .id(order.getId())
                    .totalCost(order.getTotalCost())
                    .paymentData(order.getPaymentData())
                    .itemsDTO(itemMapper.mapToSetDTOS(order.getItems()))
                    .isBought(order.getIsBought())
                    .build();
            set.add(orderDTO);
        }
        return set;
    }

}
