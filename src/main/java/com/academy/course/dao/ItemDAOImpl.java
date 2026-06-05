package com.academy.course.dao;

import com.academy.course.model.Item;

public class ItemDAOImpl extends DAOImpl<Item> implements ItemDAO{


    public ItemDAOImpl() {
        super(Item.class);
    }
}
