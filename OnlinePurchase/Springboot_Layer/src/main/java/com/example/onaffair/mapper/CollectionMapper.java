package com.example.onaffair.mapper;

import com.example.onaffair.dto.CollectionResponseDto;
import com.example.onaffair.entity.Collection;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionMapper {
    List<Collection> getAll();

    boolean insert(Collection collection);

    boolean update(Collection collection);

    boolean delete(Integer userId, Integer movieId);

    List<Collection> selectByIf(Collection collection);

    Collection selectById(@Param("userId") Integer userId, @Param("movieId") Integer movieId);

    List<CollectionResponseDto> getCollectionByMovieIdList(List<Integer> movieIdList);

}
