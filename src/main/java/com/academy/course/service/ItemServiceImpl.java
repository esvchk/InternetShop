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

import java.sql.SQLException;
import java.util.Set;

public class ItemServiceImpl implements ItemService{

    private final ItemDAO itemDAO = new ItemDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ItemMapper itemMapper = MapperFactory.getItemMapperMapper();


    @Override
    public ItemDTO getItemById(Integer id) throws SQLException {
        return itemMapper.mapToDTO(itemDAO.get(id));
    }

    @Override
    public void deleteItem(ItemDTO itemDTO) throws SQLException {
        Item item = itemDAO.get(itemDTO.getId());
        itemDAO.delete(item);
    }

    @Override
    public Set<ItemDTO> getAllItems() {
        return itemMapper.mapToSetDTOS(itemDAO.getAllItems());
    }

    @Override
    public Set<ItemDTO> getAllItemsFromOrder(Integer orderId) throws SQLException {
        if (orderId != null) {
            Order order = orderDAO.get(orderId);
            Set<Item> items = order.getItems();
            return itemMapper.mapToSetDTOS(items);
        } else
            throw new NullPointerException();
    }
}
