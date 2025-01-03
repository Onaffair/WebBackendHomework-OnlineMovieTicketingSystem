package com.example.onaffair.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.onaffair.entity.Actor;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
public interface ActorMapper extends BaseMapper<Actor> {
    List<Actor> getAll();

    int insert(Actor actor);

    boolean update(Actor actor);

    boolean delete(@Param("actorId") Integer actorId);

    List<Actor> selectByIf(Actor actor);
}
