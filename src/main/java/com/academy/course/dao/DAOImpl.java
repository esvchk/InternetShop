package com.academy.course.dao;

import com.academy.course.utils.HibernateUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.sql.SQLException;

public class DAOImpl<T> implements DAO<T>{

    private final Class<T> tClass;

    public DAOImpl(Class<T> tClass) {
        this.tClass = tClass;
    }

    protected EntityManager getEm(){
        return HibernateUtil.getEntityManager();
    }


    @Override
    public void save(T entity) throws SQLException {
        begin();
        getEm().persist(entity);
        commit();
    }

    @Override
    public T get(Serializable id) throws SQLException {
        return getEm().find(tClass,id);
    }

    @Override
    public void update(T entity) throws SQLException {
        begin();
        getEm().merge(entity);
        commit();
    }

    @Override
    public void delete(T t) throws SQLException {
        begin();
        getEm().remove(t);
        commit();
    }

    @Override
    public void begin() {
        getEm().getTransaction().begin();
    }

    @Override
    public void commit() {
        getEm().getTransaction().commit();
    }
}
