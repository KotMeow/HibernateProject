package com.meow;

import com.meow.dal.Storage;
import com.meow.model.Actor;
import com.meow.model.Movie;
import com.meow.util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Actor user = new Actor();
        Storage storage = new Storage(user);
        storage.beginTransaction();
        user.setName("Kamil");
        user.setRole("Bond");
        storage.insert(user);
        storage.commit();
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
//
//        long id = 1;
//        Movie movie1 = session.load(Movie.class, id );
//        for (Actor actortemp : movie1.getActors()) {
//            System.out.println(actortemp.getName() + " " + actortemp.getRole());
//        }
//        session.getTransaction().commit();

    }
}
