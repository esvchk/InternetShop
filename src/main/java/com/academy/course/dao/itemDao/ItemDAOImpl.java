package com.academy.course.dao.itemDao;

import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Item;
import com.academy.course.model.Order;

import java.util.HashSet;
import java.util.Set;

public class ItemDAOImpl extends DAOImpl<Item> implements ItemDAO {


    public ItemDAOImpl() {
        super(Item.class);
    }

    @Override
    public Set<Item> getAllItems() {
        return new HashSet<>(getEm().createQuery("from Item item", Item.class).getResultList());
    }
}
