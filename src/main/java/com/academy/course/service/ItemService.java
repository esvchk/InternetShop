package com.academy.course.service;

import com.academy.course.dto.ItemDTO;

import java.sql.SQLException;
import java.util.Set;

public interface ItemService {
    void updateItem(Integer oldValue,ItemDTO newValue);
    ItemDTO getItem(Integer id) throws SQLException;
    void deleteItem(ItemDTO itemDTO) throws SQLException;
    Set<ItemDTO> getAllItems();

}
