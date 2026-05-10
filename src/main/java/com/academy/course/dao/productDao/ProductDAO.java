package com.academy.course.dao.productDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Product;

import java.util.List;

public interface ProductDAO extends DAO<Product> {
    List<Product> getAllProducts();
    List<Product> getByName(String name);

}
