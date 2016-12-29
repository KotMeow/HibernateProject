package com.meow.dao;

import java.util.List;
import com.meow.model.Movie;
import com.meow.model.Actor;

public interface MovieDAO
{
    public List<Movie> getAllMovies();
    public Movie getMovieById(Long id);
    public boolean addMovie(Movie movie);
    public boolean removeMovie(Movie movie);
    public boolean removeAllMovies();
    public boolean updateMovie(Movie movie);
    public List<Movie> getMoviesByName(String name);
    public List<Actor> getMovieActors(Movie movie);
}
