package com.academy.course.dao.productDao;

import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Product;
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
    public Set<Product> getByName(String name) {
        return new HashSet<>(getEm().createQuery("from Product product where product.name like (:name)", Product.class)
                .setParameter("name","%" + name + "%")
                .getResultList());
    }

}
