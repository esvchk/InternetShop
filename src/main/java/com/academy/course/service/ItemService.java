package com.academy.course.service;

import com.academy.course.dto.ItemDTO;
import com.academy.course.dto.OrderDTO;

import java.sql.SQLException;
import java.util.Set;

public interface ItemService {
    ItemDTO getItemById(Integer id) throws SQLException;
    void deleteItem(ItemDTO itemDTO) throws SQLException;
    Set<ItemDTO> getAllItems();
    Set<ItemDTO> getAllItemsFromOrder(Integer orderId) throws SQLException;

}
