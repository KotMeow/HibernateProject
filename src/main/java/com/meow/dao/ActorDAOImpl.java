package com.meow.dao;

import com.meow.model.Actor;
import com.meow.model.Movie;
import org.hibernate.Session;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
@Repository
@Transactional
public class ActorDAOImpl implements ActorDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Actor> getAllActors() {
        List<Actor> depts = manager.createQuery("Select a From Actor a", Actor.class).getResultList();
        return depts;
    }

    @Override
    public Actor getActorById(Long id) {
        return manager.find(Actor.class, id);
    }

    @Override
    public boolean addActor(Actor actor) {
        try{
            manager.persist(actor);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean removeActor(Actor actor) {

        try{
            manager.remove(actor);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean removeAllActor() {

        try{
            Query query = manager.createNativeQuery("DELETE FROM Actor");
            query.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
