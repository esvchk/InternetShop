package com.academy.course.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateUtil {
    private static final EntityManagerFactory ENTITY_MANAGER_FACTORY =
            Persistence.createEntityManagerFactory("academy");

    private static final ThreadLocal<EntityManager> threadLocalEm = new ThreadLocal<>();

    public static EntityManager getEntityManager() {
        EntityManager em = threadLocalEm.get();
        if (em == null || !em.isOpen()) {
            em = ENTITY_MANAGER_FACTORY.createEntityManager();
            threadLocalEm.set(em);
        }
        return em;
    }

    public static void closeEntityManager() {
        EntityManager em = threadLocalEm.get();
        if (em != null) {
            if (em.isOpen()) {
                em.close();
            }
            threadLocalEm.remove();
        }
    }

    public static void closeFactory() {
        if (ENTITY_MANAGER_FACTORY.isOpen()) {
            ENTITY_MANAGER_FACTORY.close();
        }
    }
}
