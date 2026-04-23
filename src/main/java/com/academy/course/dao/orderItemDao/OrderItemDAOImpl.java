package com.academy.course.dao.orderItemDao;

import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Order;
import com.academy.course.model.OrderItem;
import com.academy.course.model.Product;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OrderItemDAOImpl extends DAOImpl<OrderItem> implements OrderItemDAO{

    public OrderItemDAOImpl() {
        super(OrderItem.class);
    }

    @Override
    public Set<OrderItem> getProductsOfOrder(Order order) {
        return new HashSet<>(order.getOrderItems());
    }


    @Override
    public void deleteProductOfOrder(Integer productId,Integer orderId) throws SQLException {
        OrderItem orderItem = getEm().createQuery(
                        "SELECT oi FROM OrderItem oi " +
                                "WHERE oi.order.id = :orderId AND oi.product.id = :productId", OrderItem.class)
                .setParameter("orderId", orderId)
                .setParameter("productId", productId)
                        .getSingleResult();


        getEm().remove(orderItem);
    }
}
