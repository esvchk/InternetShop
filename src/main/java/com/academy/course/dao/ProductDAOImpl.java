package com.academy.course.dao;

import com.academy.course.model.Product;

import java.util.List;

public class ProductDAOImpl extends DAOImpl<Product> implements ProductDAO{

    public ProductDAOImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        return getEm().createQuery("from Product product", Product.class).getResultList();
    }
}
