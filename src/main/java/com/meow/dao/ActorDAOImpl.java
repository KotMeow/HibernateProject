package com.meow.dao;

import com.meow.model.Actor;
import com.meow.model.Movie;
import com.meow.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
public class ActorDAOImpl implements ActorDAO {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    @Override
    public List<Actor> getAllActors() {
        String hql = "FROM Actor";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public Actor getActorById(Long id) {
        return session.find(Actor.class, id);
    }

    @Override
    public boolean addActor(Actor actor) {
        session.beginTransaction();
        try{
            session.persist(actor);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean removeActor(Actor actor) {
        session.beginTransaction();
        try{
            session.remove(actor);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean removeAllActor() {

        session.beginTransaction();
        try{
            String hql = "DELETE from Actor ";
            Query query = session.createQuery(hql);
            query.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        session.getTransaction().commit();
        return true;
    }
}
