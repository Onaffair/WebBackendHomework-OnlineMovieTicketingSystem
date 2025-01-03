package com.example.onaffair.service;
import com.example.onaffair.entity.Actor;
import java.util.List;

public interface ActorService {

    List<Actor> getAll();

    boolean insert(Actor actor);

    boolean update(Actor actor);

    boolean delete(Integer actorId);

    List<Actor> selectByIf(Actor actor);

}
