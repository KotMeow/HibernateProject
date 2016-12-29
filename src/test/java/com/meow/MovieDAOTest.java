package com.meow;

import com.meow.dao.ActorDAO;
import com.meow.dao.MovieDAO;
import com.meow.model.Actor;
import com.meow.model.Movie;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieDAOTest {

    @Autowired
    private MovieDAO movieDAO;
    @Autowired
    private ActorDAO actorDAO;

    private int moviesSize;
    private int actorsSize;
    @Before
    public void size() {
        moviesSize = movieDAO.getAllMovies().size();
        actorsSize = actorDAO.getAllActors().size();
    }
    @Test
    @Transactional
    @Rollback
    public void testAddMovie() {
        Movie movie = new Movie();
        movie.setTitle("Batman");
        movie.setGenre("Fantasy");
        movieDAO.addMovie(movie);
        Assert.assertEquals(movie.getTitle(), movieDAO.getMovieById(movie.getId()).getTitle());
    }

    @Test
    @Transactional
    @Rollback
    public void testAddActor() {
        Actor actor = new Actor();
        actor.setRole("Batman");
        actor.setName("Kamil Kot");
        actorDAO.addActor(actor);
        Assert.assertEquals(actor.getName(), actorDAO.getActorById(actor.getId()).getName());
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
        Assert.assertEquals(movieDAO.getAllMovies().size(), moviesSize+2);
        movieDAO.removeMovie(movie2);
        Assert.assertEquals(movieDAO.getAllMovies().size(), moviesSize+1);
        Assert.assertNull(movieDAO.getMovieById(movie2.getId()));
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
        Assert.assertEquals(actorDAO.getAllActors().size(), actorsSize+2);
        actorDAO.removeActor(actor2);
        Assert.assertEquals(actorDAO.getAllActors().size(), actorsSize+1);
        Assert.assertNull(actorDAO.getActorById(actor2.getId()));
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateMovie() {
        Movie movie1 = new Movie();
        movie1.setTitle("Batman");
        movie1.setGenre("Fantasy");
        Movie movie2 = new Movie();
        movie2.setTitle("Inception");
        movie2.setGenre("Drama");
        movieDAO.addMovie(movie1);
        movieDAO.addMovie(movie2);
        Assert.assertEquals(movieDAO.getAllMovies().size(), moviesSize+2);
        Assert.assertEquals("Inception", movieDAO.getMovieById(movie2.getId()).getTitle());
        Movie movieUpdate = movieDAO.getMovieById(movie2.getId());
        movieUpdate.setTitle("Conjuring");
        movieDAO.updateMovie(movieUpdate);
        Assert.assertEquals("Conjuring", movieDAO.getMovieById(movie2.getId()).getTitle());
        Assert.assertEquals("Batman", movieDAO.getMovieById(movie1.getId()).getTitle());
    }

    @Test
    @Transactional
    @Rollback
    public void testUpdateActor() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        actor1.setName("Kamil Kot");
        actor1.setRole("James Bond");
        actor2.setName("Leonardo");
        actor2.setRole("Batman");
        actorDAO.addActor(actor1);
        actorDAO.addActor(actor2);
        Assert.assertEquals(actorDAO.getAllActors().size(), actorsSize+2);
        Assert.assertEquals("Leonardo", actorDAO.getActorById(actor2.getId()).getName());
        Actor actorUpdate = actorDAO.getActorById(actor2.getId());
        actorUpdate.setName("Tom");
        actorDAO.updateActor(actorUpdate);
        Assert.assertEquals("Tom", actorDAO.getActorById(actor2.getId()).getName());
        Assert.assertEquals("Kamil Kot", actorDAO.getActorById(actor1.getId()).getName());
    }

    @Test
    @Transactional
    @Rollback
    public void searchTest() {
        int i = movieDAO.getMoviesByName("Batman").size();
        Movie movie = new Movie();
        movie.setTitle("Batman");
        movieDAO.addMovie(movie);
        Assert.assertEquals(movieDAO.getMoviesByName("Batman").size(), i+1);
    }

    @Test
    @Transactional
    @Rollback
    public void actorsFromMovieTest(){
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        Movie movie1 = new Movie();
        List actors = new ArrayList();
        movie1.setTitle("Batman");
        movie1.setGenre("Fantasy");
        movieDAO.addMovie(movie1);
        Assert.assertEquals(movieDAO.getMovieActors(movie1).size(), 0);
        actor1.setName("Kamil Kot");
        actor1.setRole("James Bond");
        actor2.setName("Leonardo");
        actor2.setRole("Batman");
        actorDAO.addActor(actor1);
        actorDAO.addActor(actor2);
        movie1.getActors().add(actor1);
        movie1.getActors().add(actor2);
        Assert.assertEquals(movieDAO.getMovieActors(movie1).size(), 2);
    }
}
