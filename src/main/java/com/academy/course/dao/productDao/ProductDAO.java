package com.academy.course.dao.productDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Product;

import java.util.Set;

public interface ProductDAO extends DAO<Product> {
    Set<Product> getAllProducts();
    Product getByName(String name);

}
