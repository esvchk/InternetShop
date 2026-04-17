package com.academy.course.dao;

import java.io.Serializable;
import java.sql.SQLException;

public interface DAO <T>{
    void save(T entity) throws SQLException;

    T get(Serializable id) throws SQLException;

    void update(T entity) throws SQLException;

    void delete(Serializable id) throws SQLException;

    void begin();

    void commit();
}
