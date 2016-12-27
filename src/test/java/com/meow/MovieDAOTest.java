package com.meow;

import com.meow.dao.MovieDAO;
import com.meow.model.Actor;
import com.meow.model.Movie;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
@ContextConfiguration(locations = "classpath:application-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class MovieDAOTest {

    @Autowired
    MovieDAO movieDAO;
    @Test
    public void testWrite() {
        Movie movie = new Movie();
        movie.setTitle("Batman");
        movie.setGenre("Fantasy");
        movieDAO.addMovie(movie);
        List<Movie> movies = movieDAO.getAllMovies();
        Assert.assertEquals(movie.getTitle(), movies.get(0).getTitle());
    }
}
