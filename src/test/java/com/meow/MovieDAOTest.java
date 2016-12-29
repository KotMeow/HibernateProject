package com.meow;

import com.meow.dao.ActorDAO;
import com.meow.dao.MovieDAO;
import com.meow.model.Actor;
import com.meow.model.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieDAOTest {

    @Autowired
    MovieDAO movieDAO;
    @Autowired
    ActorDAO actorDAO;

    @Test
    @Transactional
    @Rollback
    public void testAddMovie() {
        Movie movie = new Movie();
        movie.setTitle("Batman");
        movie.setGenre("Fantasy");
        movieDAO.addMovie(movie);
        List<Movie> movies = movieDAO.getAllMovies();
        Assert.assertEquals(movie.getTitle(), movies.get(0).getTitle());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddActor() {
        Actor actor = new Actor();
        actor.setRole("Batman");
        actor.setName("Kamil Kot");
        actorDAO.addActor(actor);
        List<Actor> actors = actorDAO.getAllActors();
        Assert.assertEquals(actor.getName(), actors.get(0).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteMovie() {
        Movie movie1 = new Movie();
        movie1.setTitle("Batman");
        movie1.setGenre("Fantasy");
        Movie movie2 = new Movie();
        movie2.setTitle("Inception");
        movie2.setGenre("Drama");
        movieDAO.addMovie(movie1);
        movieDAO.addMovie(movie2);
        Assert.assertEquals(movieDAO.getAllMovies().size(), 2);
        movieDAO.removeMovie(movie2);
        Assert.assertEquals(movieDAO.getAllMovies().size(), 1);
        Assert.assertNull(movieDAO.getMovieById((long) 2));
        Assert.assertEquals(movieDAO.getAllMovies().size(), 1);
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteActor() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        actor1.setName("Kamil Kot");
        actor1.setRole("James Bond");
        actor2.setName("Leonardo");
        actor2.setRole("Batman");
        actorDAO.addActor(actor1);
        actorDAO.addActor(actor2);
        Assert.assertEquals(actorDAO.getAllActors().size(), 2);
        actorDAO.removeActor(actor2);
        Assert.assertEquals(actorDAO.getAllActors().size(), 1);
        Assert.assertNull(actorDAO.getActorById((long) 2));
        Assert.assertEquals(actorDAO.getAllActors().size(), 1);
    }
}
