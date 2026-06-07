package com.academy.course.dao.categoryDao;

import com.academy.course.dao.DAO;
import com.academy.course.model.Category;

import java.util.Set;

public interface CategoryDAO extends DAO<Category> {
    Set<Category> getAllCategories();
}
