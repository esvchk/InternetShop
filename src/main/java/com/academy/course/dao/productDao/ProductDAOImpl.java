package com.academy.course.dao.productDao;

import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Product;

import javax.persistence.Query;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
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
    public Set<Product> getAllProducts(int offSet, int size) {
        return new HashSet<>(getEm().createQuery("FROM Product product ORDER BY product.id", Product.class)
                .setFirstResult(offSet)
                .setMaxResults(size)
                .getResultList());
    }

    @Override
    public Long countProducts() {
        return getEm().createQuery("SELECT COUNT(product.id) from Product product", Long.class).getSingleResult();
    }

    @Override
    public Product getByName(String name) {
        Query query = getEm().createQuery("from Product product where product.name=: name", Product.class);
        query.setParameter("name",name);
        return (Product) query.getSingleResult();
    }



}
