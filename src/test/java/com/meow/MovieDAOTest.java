package com.meow;

import com.meow.dao.ActorDAO;
import com.meow.dao.MovieDAO;
import com.meow.model.Actor;
import com.meow.model.Movie;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Rollback
@Transactional
public class MovieDAOTest {

    @Autowired
    private MovieDAO movieDAO;
    @Autowired
    private ActorDAO actorDAO;

    private int moviesSize;
    private int actorsSize;
    private final boolean roll = true;

    @Before
    public void size() {
        moviesSize = movieDAO.getAllMovies().size();
        actorsSize = actorDAO.getAllActors().size();
    }


    @Test
    public void testAddMovie() {
        Movie movie = new Movie();
        movie.setTitle("Batman");
        movie.setGenre("Fantasy");
        movieDAO.addMovie(movie);
        Assert.assertEquals(movie.getTitle(), movieDAO.getMovieById(movie.getId()).getTitle());
        Assert.assertEquals(movie.getGenre(), movieDAO.getMovieById(movie.getId()).getGenre());
    }

    @Test
    public void testAddActor() {
        Actor actor = new Actor();
        actor.setRole("Batman");
        actor.setName("Kamil Kot");
        actorDAO.addActor(actor);
        Assert.assertEquals(actor.getName(), actorDAO.getActorById(actor.getId()).getName());
    }

    @Test
    public void testDeleteMovie() {

        Movie movie1 = new Movie();
        movie1.setTitle("Batman");
        movie1.setGenre("Fantasy");
        Movie movie2 = new Movie();
        movie2.setTitle("Inception");
        movie2.setGenre("Drama");
        movieDAO.addMovie(movie1);
        movieDAO.addMovie(movie2);

        Assert.assertEquals(movieDAO.getAllMovies().size(), moviesSize + 2);

        movieDAO.removeMovie(movie2);
        Assert.assertEquals(movieDAO.getAllMovies().size(), moviesSize + 1);
        Assert.assertNull(movieDAO.getMovieById(movie2.getId()));
        Assert.assertEquals("Batman", movieDAO.getMovieById(movie1.getId()).getTitle());
    }

    @Test
    public void testDeleteActor() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        actor1.setName("Kamil Kot");
        actor1.setRole("James Bond");
        actor2.setName("Leonardo");
        actor2.setRole("Batman");
        actorDAO.addActor(actor1);
        actorDAO.addActor(actor2);

        Assert.assertEquals(actorDAO.getAllActors().size(), actorsSize + 2);
        actorDAO.removeActor(actor2);
        Assert.assertEquals(actorDAO.getAllActors().size(), actorsSize + 1);
        Assert.assertNull(actorDAO.getActorById(actor2.getId()));
        Assert.assertEquals("Kamil Kot", actorDAO.getActorById(actor1.getId()).getName());
    }

    @Test
    public void testUpdateMovie() {
        Movie movie1 = new Movie();
        movie1.setTitle("Batman");
        movie1.setGenre("Fantasy");
        Movie movie2 = new Movie();
        movie2.setTitle("Inception");
        movie2.setGenre("Drama");
        movieDAO.addMovie(movie1);
        movieDAO.addMovie(movie2);

        Assert.assertEquals(movieDAO.getAllMovies().size(), moviesSize + 2);
        Assert.assertEquals("Inception", movieDAO.getMovieById(movie2.getId()).getTitle());

        Movie movieUpdate = movieDAO.getMovieById(movie2.getId());
        movieUpdate.setTitle("Conjuring");
        movieDAO.updateMovie(movieUpdate);

        Assert.assertEquals("Conjuring", movieDAO.getMovieById(movie2.getId()).getTitle());
        Assert.assertEquals("Batman", movieDAO.getMovieById(movie1.getId()).getTitle());
    }

    @Test
    public void testUpdateActor() {
        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        actor1.setName("Kamil Kot");
        actor1.setRole("James Bond");
        actor2.setName("Leonardo");
        actor2.setRole("Batman");
        actorDAO.addActor(actor1);
        actorDAO.addActor(actor2);

        Assert.assertEquals(actorDAO.getAllActors().size(), actorsSize + 2);
        Assert.assertEquals("Leonardo", actorDAO.getActorById(actor2.getId()).getName());

        Actor actorUpdate = actorDAO.getActorById(actor2.getId());
        actorUpdate.setName("Tom");
        actorDAO.updateActor(actorUpdate);

        Assert.assertEquals("Tom", actorDAO.getActorById(actor2.getId()).getName());
        Assert.assertEquals("Kamil Kot", actorDAO.getActorById(actor1.getId()).getName());
    }

    @Test
    public void searchTest() {
        int i = movieDAO.getMoviesByName("Batman").size();
        Movie movie = new Movie();
        movie.setTitle("Batman");
        movieDAO.addMovie(movie);
        Assert.assertEquals(movieDAO.getMoviesByName("Batman").size(), i + 1);
        Assert.assertEquals("Batman", movieDAO.getMoviesByName("Batman").get(0).getTitle());
    }

    @Test
    public void actorsFromMovieTest() {

        Movie movie1 = new Movie();
        movie1.setTitle("Batman");
        movie1.setGenre("Fantasy");
        movieDAO.addMovie(movie1);
        Assert.assertEquals(movieDAO.getMovieActors(movie1).size(), 0);

        Actor actor1 = new Actor();
        Actor actor2 = new Actor();
        List actors = new ArrayList();
        actor1.setName("Kamil Kot");
        actor1.setRole("James Bond");
        actor2.setName("Leonardo");
        actor2.setRole("Batman");
        actorDAO.addActor(actor1);
        actorDAO.addActor(actor2);

        movie1.getActors().add(actor1);
        movie1.getActors().add(actor2);
        Assert.assertEquals(movieDAO.getMovieActors(movie1).size(), 2);
        Assert.assertEquals("Kamil Kot", movieDAO.getMovieActors(movie1).get(0).getName());
    }

    @Test
    @Transactional
    public void deleteActorFromMovie() {
        Movie movie1 = new Movie();
        movie1.setTitle("Batman");
        movie1.setGenre("Fantasy");
        movieDAO.addMovie(movie1);
        Actor actor = new Actor();
        actor.setName("Kot");
        actorDAO.addActor(actor);
        movie1.getActors().add(actor);
        Assert.assertEquals(1, movieDAO.getMovieActors(movie1).size());
        movie1.getActors().remove(actor);
        Assert.assertEquals(0, movieDAO.getMovieActors(movie1).size());
        Assert.assertEquals("Kot", actorDAO.getActorById(actor.getId()).getName());
    }
}
