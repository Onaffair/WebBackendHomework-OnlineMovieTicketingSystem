package com.example.onaffair.service;

import com.example.onaffair.dto.CollectionResponseDto;
import com.example.onaffair.entity.Collection;
import java.util.List;

public interface CollectionService {
    List<Collection> getAllCollections();

    boolean addCollection(Collection collection);

    boolean updateCollection(Collection collection);

    boolean deleteCollection(Integer userId, Integer movieId);

    List<Collection> findCollectionsByCondition(Collection collection);

    Collection findCollectionById(Integer userId, Integer movieId);

    List<CollectionResponseDto> getCollectionByMovieIdList(List<Integer> movieIdList);

}
