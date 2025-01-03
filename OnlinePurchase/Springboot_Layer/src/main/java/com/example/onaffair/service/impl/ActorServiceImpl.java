package com.example.onaffair.service.impl;

import com.example.onaffair.entity.Actor;
import com.example.onaffair.mapper.ActorMapper;
import com.example.onaffair.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ActorServiceImpl implements ActorService {

    @Autowired
    private ActorMapper actorMapper;

    public List<Actor> getAll() {
        return actorMapper.getAll();
    }

    public boolean insert(Actor actor) {
        return actorMapper.insert(actor)>0;
    }

    public boolean update(Actor actor) {
        return actorMapper.update(actor);
    }

    public boolean delete(Integer actorId) {
        return actorMapper.delete(actorId);
    }

    public List<Actor> selectByIf(Actor actor) {
        return actorMapper.selectByIf(actor);
    }
}
