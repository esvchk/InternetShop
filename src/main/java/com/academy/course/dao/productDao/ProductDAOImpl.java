package com.academy.course.dao.productDao;

import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Product;

import javax.persistence.Query;
import java.util.List;

public class ProductDAOImpl extends DAOImpl<Product> implements ProductDAO{

    public ProductDAOImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> getAllProducts() {
        Query query = getEm().createQuery("from Product", Product.class);
        return query.getResultList();
    }

    @Override
    public List<Product> getByName(String name) {
        Query query = getEm().createQuery("from Product product where product.name=: name", Product.class);
        query.setParameter("name",name);
        return query.getResultList();
    }



}
