package com.meow.dao;

import com.meow.model.Actor;

import java.util.List;

/**
 * Created by Meow on 27.12.2016.
 */
public interface ActorDAO {
    public List<Actor> getAllActors();
    public Actor getActorById(Long id);
    public boolean addActor(Actor actor);
    public boolean removeActor(Actor actor);
    public boolean updateActor(Actor actor);
    public boolean removeAllActor();
}
