package com.meow.dao;

import com.meow.model.Actor;
import com.meow.model.Movie;
import com.meow.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
@Repository
@Transactional
public class MovieDAOImpl implements MovieDAO {

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    @Override
    public List<Movie> getAllMovies() {
        String hql = "FROM Movie";
        Query query = session.createQuery(hql);
        return query.list();
    }

    @Override
    public Movie getMovieById(Long id) {
        return session.find(Movie.class, id);
    }

    @Override
    public boolean addMovie(Movie movie) {
        session.beginTransaction();
        try{
            session.persist(movie);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean removeMovie(Movie movie) {
        session.beginTransaction();
        try{
            session.remove(movie);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean removeAllMovies() {
        session.beginTransaction();
        try{
            String hql = "DELETE from Movie";
            Query query = session.createQuery(hql);
            query.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        session.getTransaction().commit();
        return true;
    }

    @Override
    public List<Actor> getMovieActors(Movie movie) {
        return movie.getActors();
    }
}

