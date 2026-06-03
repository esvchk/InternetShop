package com.academy.course.dao.productDao;

import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Product;

import javax.persistence.Query;
import java.util.HashSet;
import java.util.Set;

public class ProductDAOImpl extends DAOImpl<Product> implements ProductDAO{

    public ProductDAOImpl() {
        super(Product.class);
    }

    @Override
    public Set<Product> getAllProducts() {
        return new HashSet<>(getEm().createQuery("from Product", Product.class).getResultList());
    }

    @Override
    public Product getByName(String name) {
        Query query = getEm().createQuery("from Product product where product.name=: name", Product.class);
        query.setParameter("name",name);
        return (Product) query.getSingleResult();
    }



}
