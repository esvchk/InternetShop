package com.academy.course.dao.orderItemDao;

import com.academy.course.dao.DAO;
import com.academy.course.dao.DAOImpl;
import com.academy.course.dto.OrderItemDTO;
import com.academy.course.model.OrderItem;

public class OrderItemDAOImpl extends DAOImpl<OrderItem> implements OrderItemDao {
    public OrderItemDAOImpl() {
        super(OrderItem.class);
    }


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
