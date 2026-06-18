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
import com.academy.course.mapper.factory.MapperFactory;
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
    private final OrderDAO orderDAO ;
    private final ProductDAO productDAO ;
    private final ItemDAO itemDAO;
    private final ProductMapper productMapper;
    private final ItemMapper itemMapper ;
    private final OrderMapper orderMapper ;
    private final IdValidatorFactory factory;
    private final BaseOrderValidator baseOrderValidator;
    private final BusinessOrderValidator businessOrderValidator;
    private final BusinessItemValidator businessItemValidator;
    private final ProductService productService;
    private final ItemService itemService;

    public OrderServiceImpl(OrderDAO orderDAO, ProductDAO productDAO, ItemDAO itemDAO, ProductMapper productMapper, ItemMapper itemMapper, OrderMapper orderMapper, IdValidatorFactory factory, BaseOrderValidator baseOrderValidator, BusinessOrderValidator businessOrderValidator, BusinessItemValidator businessItemValidator, ProductService productService, ItemService itemService) {
        this.orderDAO = orderDAO;
        this.productDAO = productDAO;
        this.itemDAO = itemDAO;
        this.productMapper = productMapper;
        this.itemMapper = itemMapper;
        this.orderMapper = orderMapper;
        this.factory = factory;
        this.baseOrderValidator = baseOrderValidator;
        this.businessOrderValidator = businessOrderValidator;
        this.businessItemValidator = businessItemValidator;
        this.productService = productService;
        this.itemService = itemService;
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
                    .discount(Discount.ZERO_VALUE)
                    .build();
            order.addItem(item);
        }

        if (product.getProductLimit() != null)
            productService.setProductLimit(productMapper.mapToDTO(product),
                    product.getProductLimit() - quantity);

        countAmountOfAllItems(orderDTO);

        orderDAO.update(order);

        logger.info("Product {} has been successfully added to order {} with quantity {} "
                , productDTO, orderDTO, quantity);
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
        Set<ItemDTO> items = itemMapper.mapToSetDTOS(order.getItems());
        double totalPrice = 0.0;
        for (ItemDTO itemDTO : items){
            double priceOfItem = itemDTO.getProductDTO().getPrice() * itemDTO.getQuantity();
            totalPrice += itemDTO.getDiscount().countCostWithDiscount(priceOfItem);
        }
        order.setTotalCost(totalPrice);
        orderDAO.update(order);
        return order.getTotalCost();
    }

    @Override
    public void setDiscountOnOrder(Integer orderId, Discount discount) throws SQLException {
        factory.getOrderValidator().validateId(orderId);
        Order order = orderDAO.get(orderId);
        Set<ItemDTO> items = itemMapper.mapToSetDTOS(order.getItems());
        for (ItemDTO itemDTO : items){
            businessItemValidator.setDiscountOnItem(discount);
           itemService.setDiscountOnItem(itemDTO.getId(),discount);
        }
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
