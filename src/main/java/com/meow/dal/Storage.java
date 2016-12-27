package com.meow.dal;

/**
 * Created by Meow on 27.12.2016.
 */
import com.meow.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class Storage {

    public static SessionFactory getSessionFactory() {
        return HibernateUtil.getSessionFactory();
    }

    private Object entity;
    private Session session;

    public void beginTransaction() {
        session = Storage.getSessionFactory().getCurrentSession();
        session.beginTransaction();
    }

    public void commit() {
        session.getTransaction().commit();
    }

    public Storage(Object entity) {
        this.entity = entity;
    }

    public void update(Object entity) {
        session.update(entity);
    }

    public Long insert(Object entity) {
        return (Long) session.save(entity);
    }

    public void delete(Object entity) {
        session.delete(entity);
    }

    public Object getById(Long id) {
        return (Object) session.get(entity.getClass(), id);
    }
}