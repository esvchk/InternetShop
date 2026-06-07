package com.academy.course.dao.categoryDao;

import com.academy.course.dao.DAOImpl;
import com.academy.course.model.Category;

import java.util.HashSet;
import java.util.Set;

public class CategoryDAOImpl extends DAOImpl<Category> implements CategoryDAO {

    public CategoryDAOImpl() {
        super(Category.class);
    }

    @Override
    public Set<Category> getAllCategories() {
        return new HashSet<>(getEm().createQuery("from Category category", Category.class).getResultList());
    }
}
