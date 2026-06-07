package com.academy.course.service;


import com.academy.course.dao.ItemDAO;
import com.academy.course.dao.ItemDAOImpl;
import com.academy.course.dao.customerDao.CustomerDAO;
import com.academy.course.dao.customerDao.CustomerDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.CustomerDTO;
import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.mapper.*;
import com.academy.course.model.Customer;
import com.academy.course.model.Item;
import com.academy.course.model.Order;
import com.academy.course.model.Product;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {

    private static final Logger log = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final CustomerDAO customerDAO = new CustomerDAOImpl();
    private final ItemDAO itemDAO = new ItemDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();
    private final ItemMapper itemMapper = new ItemMapper(productMapper);
    private final OrderMapper orderMapper = new OrderMapper(itemMapper);


    @Override
    public OrderDTO findOrderById(Serializable orderId) throws SQLException {
        return orderMapper.mapToDTO(orderDAO.get(orderId));
    }

    @Override
    public Set<OrderDTO> getAllOrdersWithItems() {
        return orderMapper.mapToSetDTOS(orderDAO.getAllOrders());
    }

    @Override
    public void deleteOrder(OrderDTO orderDTO) throws SQLException {
        if (orderDTO.getId() != null) {
            orderDAO.delete(orderDAO.get(orderDTO.getId()));
        } else
            throw new NullPointerException();
    }

    @Override
    public void addProductToOrder(ProductDTO productDTO,
                                  OrderDTO orderDTO, Integer quantity) throws SQLException {
        if (productDTO.getId() == null) {
            throw new NullPointerException();
        } else if (orderDTO.getId() == null) {
            throw new NullPointerException();
        }
        Order order = orderDAO.get(orderDTO.getId());
        if (order.getIsBought() != null && !order.getIsBought()) {
            Product product = productDAO.get(productDTO.getId());
            Optional<Item> items = order.getItems().stream()
                    .filter(item1 -> item1.getProduct().getId().equals(product.getId()))
                    .findFirst();
            if (items.isPresent()) {
                items.get().setProductQuantity(items.get().getProductQuantity() + quantity);
            } else {
                Item item = Item.builder()
                        .productQuantity(quantity)
                        .product(product)
                        .order(order)
                        .build();
                order.addItem(item);
            }
            orderDAO.update(order);
        } else {
            log.warn("Order {} already bought", order.getId());
        }
    }

    @Override
    public Set<OrderDTO> getAllOrdersOfCustomer(CustomerDTO customerDTO) throws SQLException {
        if (customerDTO != null) {
            Customer customer = customerDAO.get(customerDTO.getId());
            Set<Order> orders = customer.getOrders();
            return orderMapper.mapToSetDTOS(orders);
        } else
            throw new NullPointerException();
    }

    @Override
    public void deleteItemFromOrder(ItemDTO itemDTO, Integer orderId,Integer quantity) throws SQLException {
        Order order = orderDAO.get(orderId);
        Item item = itemDAO.get(itemDTO.getId());
        if (item.getProductQuantity().equals(quantity) || quantity > item.getProductQuantity()) {
            itemDAO.delete(item);
        } else {
            item.setProductQuantity(item.getProductQuantity() - quantity);
        }
        orderDAO.update(order);
    }

    @Override
    public void buyOrder(OrderDTO orderDTO) throws SQLException {
        if (orderDTO.getId() != null) {
            Order order = orderDAO.get(orderDTO.getId());
            if (!order.getIsBought()) {
                order.setIsBought(true);
                orderDAO.update(order);
            } else
                log.warn("Order {} already has been purchased", orderDTO);

        } else
            throw new NullPointerException();

    }


}
