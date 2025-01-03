package com.example.onaffair.service.impl;

import com.example.onaffair.dto.CollectionResponseDto;
import com.example.onaffair.entity.Collection;
import com.example.onaffair.mapper.CollectionMapper;
import com.example.onaffair.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public List<Collection> getAllCollections() {
        return collectionMapper.getAll();
    }

    @Override
    public boolean addCollection(Collection collection) {
        return collectionMapper.insert(collection);
    }

    @Override
    public boolean updateCollection(Collection collection) {
        return collectionMapper.update(collection);
    }

    @Override
    public boolean deleteCollection(Integer userId, Integer movieId) {
        return collectionMapper.delete(userId, movieId);
    }

    @Override
    public List<CollectionResponseDto> getCollectionByMovieIdList(List<Integer> movieIdList) {
        return collectionMapper.getCollectionByMovieIdList(movieIdList);
    }

    @Override
    public List<Collection> findCollectionsByCondition(Collection collection) {
        return collectionMapper.selectByIf(collection);
    }

    @Override
    public Collection findCollectionById(Integer userId, Integer movieId) {
        return collectionMapper.selectById(userId, movieId);
    }
}
