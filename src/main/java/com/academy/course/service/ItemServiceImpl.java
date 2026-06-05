package com.academy.course.service;

import com.academy.course.dao.ItemDAO;
import com.academy.course.dao.ItemDAOImpl;
import com.academy.course.dao.orderDao.OrderDAO;
import com.academy.course.dao.orderDao.OrderDAOImpl;
import com.academy.course.dao.productDao.ProductDAO;
import com.academy.course.dao.productDao.ProductDAOImpl;
import com.academy.course.dto.ItemDTO;
import com.academy.course.mapper.ItemMapper;
import com.academy.course.mapper.MapperFactory;
import com.academy.course.mapper.OrderMapper;
import com.academy.course.mapper.ProductMapper;
import com.academy.course.model.Item;
import com.academy.course.model.Order;
import com.academy.course.model.Product;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;

public class ItemServiceImpl implements ItemService{

    private final ItemDAO itemDAO = new ItemDAOImpl();
    private final ProductDAO productDAO = new ProductDAOImpl();
    private final OrderDAO orderDAO = new OrderDAOImpl();
    private final ItemMapper itemMapper = MapperFactory.getItemMapperMapper();
    private final OrderMapper orderMapper = MapperFactory.getOrderMapper();
    private final ProductMapper productMapper = MapperFactory.getProductMapper();


    @Override
    public void updateItem(Integer oldValue, ItemDTO newValue) {

    }

    @Override
    public ItemDTO getItem(Integer id) throws SQLException {
        return itemMapper.mapToDTO(itemDAO.get(id));
    }

    @Override
    public void deleteItem(ItemDTO itemDTO) throws SQLException {
        itemDAO.get(itemDTO.getId());
    }

    @Override
    public Set<ItemDTO> getAllItems() {
        return Collections.emptySet();
    }
}
