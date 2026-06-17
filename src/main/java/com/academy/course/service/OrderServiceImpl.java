package com.academy.course.service;


import com.academy.course.dao.itemDao.ItemDAO;
import com.academy.course.dao.itemDao.ItemDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;

import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;
import com.academy.course.dto.ProductDTO;
import com.academy.course.exception.EntityNotFoundByIdException;
import com.academy.course.mapper.*;
import com.academy.course.model.Item;
import com.academy.course.model.Order;
import com.academy.course.model.Product;
import com.academy.course.service.validator.*;
import com.academy.course.utils.Discount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LogManager.getLogger(OrderServiceImpl.class);
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final ItemDAO itemDAO = new ItemDAOImpl();
    private final ProductMapper productMapper = new ProductMapper();
    private final ItemMapper itemMapper = new ItemMapper(productMapper);
    private final OrderMapper orderMapper = new OrderMapper(itemMapper);
    private final IdValidatorFactory factory;
    private final BaseOrderValidator baseOrderValidator = new BaseOrderValidatorImpl();
    private final BusinessOrderValidator businessOrderValidator = new BusinessOrderValidatorImpl(orderDAO);
    private final ProductService productService;

    public OrderServiceImpl(IdValidatorFactory factory, ProductService productService) {
        this.factory = factory;

        this.productService = productService;
    }

    @Override
    public OrderDTO findOrderById(Integer orderId) throws SQLException {
        factory.getOrderValidator().validateId(orderId);
        logger.info("Finding order by id {} successfully", orderId);
        return orderMapper.mapToDTO(orderDAO.get(orderId));
    }

    @Override
    public Set<OrderDTO> getAllOrdersWithItems() {
        businessOrderValidator.validateGetAllOrders();
        logger.info("Getting all Orders successfully");
        return orderMapper.mapToSetDTOS(orderDAO.getAllOrders());
    }

    @Override
    public void deleteOrder(OrderDTO orderDTO) throws SQLException {
        factory.getOrderValidator().validateId(orderDTO.getId());
        orderDAO.delete(orderDAO.get(orderDTO.getId()));
        logger.info("Deleting order with id{} successfully", orderDTO.getId());
    }

    @Override
    public void addProductToOrder(ProductDTO productDTO,
                                  OrderDTO orderDTO, Integer quantity) throws SQLException {
        factory.getProductValidator().validateId(productDTO.getId());
        factory.getOrderValidator().validateId(orderDTO.getId());
        baseOrderValidator.validateAddProductToOrder(productDTO, orderDTO, quantity);
        Order order = orderDAO.get(orderDTO.getId());
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
        if (product.getProductLimit() != null)
            productService.setProductLimit(productMapper.mapToDTO(product),
                    product.getProductLimit() - quantity);
        orderDAO.update(order);
        logger.info("Product {} has been successfully added to order {}" +
                "with quantity {}", productDTO, orderDTO, quantity);
    }

    @Override
    public void deleteItemFromOrder(ItemDTO itemDTO, Integer orderId, Integer quantity) throws SQLException {
        factory.getOrderValidator().validateId(orderId);
        factory.getItemValidator().validateId(itemDTO.getId());
        Order order = orderDAO.get(orderId);
        Item item = itemDAO.get(itemDTO.getId());
        baseOrderValidator.validateDeleteItemFromOrder(itemDTO, orderId, quantity);
        if (item.getProductQuantity().equals(quantity)) {
            itemDAO.delete(item);
        } else {
            item.setProductQuantity(item.getProductQuantity() - quantity);
        }
        orderDAO.update(order);
        logger.info("Item {} with quantity {} has been successfully removed from order with id {}", itemDTO, quantity, orderId);
    }

    @Override
    public Double countAmountOfAllItems(OrderDTO orderDTO) throws SQLException {
        factory.getOrderValidator().validateId(orderDTO.getId());
        Order order = orderDAO.get(orderDTO.getId());
        double totalPrice = 0.0;
        for (ItemDTO itemDTO : orderDTO.getItemsDTO()){
            totalPrice += itemDTO.getProductDTO().getPrice() * itemDTO.getQuantity() * itemDTO.getDiscount().getPercentOfDiscount();
        }
        order.setTotalCost(totalPrice);
        orderDAO.update(order);
        return order.getTotalCost();
    }

    @Override
    public void buyOrder(OrderDTO orderDTO) throws SQLException {
        factory.getOrderValidator().validateId(orderDTO.getId());
        Order order = orderDAO.get(orderDTO.getId());
        baseOrderValidator.validateBuyOrder(orderDTO);
        order.setIsBought(true);
        orderDAO.update(order);
        logger.info("Order {} has been successfully bought", orderDTO);
    }
}
