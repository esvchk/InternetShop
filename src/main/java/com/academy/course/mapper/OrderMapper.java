package com.academy.course.mapper;

import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.OrderShortDTO;
import com.academy.course.model.Order;


import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderMapper implements Mapper<Order, OrderDTO>,ShortMapper<OrderShortDTO,Order>{

    private final ItemMapper itemMapper;
    private final CustomerMapper customerMapper;

    public OrderMapper(ItemMapper itemMapper, CustomerMapper customerMapper) {
        this.itemMapper = itemMapper;
        this.customerMapper = customerMapper;
    }


    @Override
    public OrderDTO mapToDTO(Order entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .itemsDTO(itemMapper.mapToListDTOS(entity.getItems()))
                .customerShortDTO(customerMapper.mapToShortDTO(entity.getCustomer()))
                .isBought(entity.getIsBought())
                .build();
    }

    @Override
    public Order mapToEntity(OrderDTO dto) {
        return Order.builder()
                .items(itemMapper.mapToListEntities(dto.getItemsDTO()))
                .isBought(dto.getIsBought())
                .build();
    }

    @Override
    public Set<Order> mapToListEntities(Set<OrderDTO> dtoSet) {
        return dtoSet.stream().map(this::mapToEntity).collect(Collectors.toSet());
    }

    @Override
    public Set<OrderDTO> mapToListDTOS(Set<Order> entitySet) {
        return entitySet.stream().map(this::mapToDTO).collect(Collectors.toSet());
    }


    @Override
    public OrderShortDTO mapToShortDTO(Order order) {
        return OrderShortDTO.builder()
                .id(order.getId())
                .isBought(order.getIsBought())
                .build();
    }
}
