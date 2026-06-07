package com.academy.course.dao.itemDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Item;

import java.util.Set;

public interface ItemDAO extends DAO<Item> {
    Set<Item> getAllItems();

}
