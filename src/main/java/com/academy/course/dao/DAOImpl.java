package com.academy.course.dao;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.sql.SQLException;

public class DAOImpl<T> implements DAO<T>{
    private Class<?> t;

    @Override
    public T save(T entity) throws SQLException {
        return null;
    }

    @Override
    public T get(Serializable id) throws SQLException {
        return null;
    }

    @Override
    public void update(T entity) throws SQLException {

    }

    @Override
    public void delete(Serializable id) throws SQLException {

    }
}
