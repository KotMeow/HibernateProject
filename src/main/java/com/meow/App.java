package com.meow;

import com.meow.dao.ActorDAO;
import com.meow.dao.ActorDAOImpl;
import com.meow.dao.MovieDAO;
import com.meow.dao.MovieDAOImpl;
import com.meow.model.Actor;
import com.meow.model.Movie;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {
//        MovieDAO movieDAO = new MovieDAOImpl();
//        ActorDAO actorDAO = new ActorDAOImpl();
//        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//        session.beginTransaction();
//
//        Actor actor1 = new Actor();
//        actor1.setName("Kamil Kot");
//        actor1.setRole("James Bond");
//        Actor actor2 = new Actor();
//        actor2.setName("Meow Meow");
//        actor2.setRole("Batman");
//
//        session.save(actor1);
//        session.save(actor2);
//        ArrayList<Actor> actors = new ArrayList<>();
//        actors.add(actor1);
//        actors.add(actor2);
//
//        Movie movie = new Movie();
//        movie.setTitle("Meow");
//        movie.setGenre("Drama");
//        movie.setReleaseYear(1939);
//        movie.setActors(actors);
//        session.save(movie);
//
//        for (Movie moviete : movieDAO.getAllMovies()) {
//            System.out.println(moviete.getTitle() + " " + moviete.getGenre());
//        }
//        Movie moviete = movieDAO.getMovieById((long) 1);
//        System.out.println(moviete.getTitle() + " " + moviete.getGenre());
//        for (Actor actortemp : moviete.getActors()) {
//            System.out.println(actortemp.getName() + " " + actortemp.getRole());
//        }
//
//        for (Actor actorte : actorDAO.getAllActors()) {
//            System.out.println(actorte.getName() + " " + actorte.getRole());
//        }
    }
}
