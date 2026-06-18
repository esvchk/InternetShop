package com.academy.course.service;

import com.academy.course.dao.itemDao.ItemDAO;
import com.academy.course.dao.itemDao.ItemDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dto.ItemDTO;
import com.academy.course.mapper.ItemMapper;
import com.academy.course.mapper.MapperFactory;
import com.academy.course.model.Item;
import com.academy.course.model.Order;
import com.academy.course.service.validator.BusinessItemValidator;
import com.academy.course.service.validator.BusinessItemValidatorImpl;
import com.academy.course.service.validator.IdValidator;
import com.academy.course.service.validator.IdValidatorFactory;
import com.academy.course.utils.Discount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.Set;

public class ItemServiceImpl implements ItemService {

    private static final Logger logger = LogManager.getLogger(ItemServiceImpl.class);
    private final ItemDAO itemDAO = new ItemDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ItemMapper itemMapper = MapperFactory.getItemMapperMapper();
    private final IdValidatorFactory factory;
    private final BusinessItemValidator businessItemValidator = new BusinessItemValidatorImpl(itemDAO,orderDAO);

    public ItemServiceImpl(IdValidatorFactory factory) {
        this.factory = factory;
    }


    @Override
    public ItemDTO getItemById(Integer id) throws SQLException {
        factory.getItemValidator().validateId(id);
        logger.info("Successful receiving item by id {}",id);
        return itemMapper.mapToDTO(itemDAO.get(id));
    }

    @Override
    public void deleteItem(ItemDTO itemDTO) throws SQLException {
        factory.getItemValidator().validateId(itemDTO.getId());
        Item item = itemDAO.get(itemDTO.getId());
        itemDAO.delete(item);
        logger.info("Successful deleting item with id {}",itemDTO.getId());
    }

    @Override
    public Set<ItemDTO> getAllItems() {
        businessItemValidator.getAllItems();
        logger.info("Successful receiving all items");
        return itemMapper.mapToSetDTOS(itemDAO.getAllItems());
    }

    @Override
    public Set<ItemDTO> getAllItemsFromOrder(Integer orderId) throws SQLException {
        factory.getOrderValidator().validateId(orderId);
        businessItemValidator.getAllItemsFromOrder(orderId);
        Order order = orderDAO.get(orderId);
        Set<Item> items = order.getItems();
        logger.info("Successful receiving all items of order with id{}",orderId);
        return itemMapper.mapToSetDTOS(items);
    }

    @Override
    public void setDiscountOnItem(Integer itemId, Discount discount) throws SQLException {
        factory.getItemValidator().validateId(itemId);
        Item item = itemDAO.get(itemId);
        businessItemValidator.setDiscountOnItem(discount);
            item.setDiscount(discount);
        itemDAO.update(item);
        logger.info("Successful setting up discount on item with id {}",itemId);
    }
}
