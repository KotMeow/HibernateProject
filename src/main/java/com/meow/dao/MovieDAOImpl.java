package com.meow.dao;

import com.meow.model.Actor;
import com.meow.model.Movie;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
@Repository
@Transactional
public class MovieDAOImpl implements MovieDAO {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public List<Movie> getAllMovies() {
        List<Movie> movies = manager.createQuery("Select a From Movie a", Movie.class).getResultList();
        return movies;
    }

    @Override
    public Movie getMovieById(Long id) {
        return manager.find(Movie.class, id);
    }

    @Override
    public boolean addMovie(Movie movie) {
        try{
            manager.persist(movie);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public boolean removeMovie(Movie movie) {

        try{
            manager.remove(movie);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean removeAllMovies() {

        try{
            Query query = manager.createNativeQuery("DELETE FROM Movie");
            query.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        try{
            manager.merge(movie);
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Movie> getMoviesByName(String name) {
        List<Movie> movies = manager.createQuery("Select a From Movie a where a.title like :custName", Movie.class).setParameter("custName", name).getResultList();
        return movies;
    }

    @Override
    public List<Actor> getMovieActors(Movie movie) {
        return movie.getActors();
    }
}

