package com.example.onaffair.controller;

import com.example.onaffair.dto.CollectionResponseDto;
import com.example.onaffair.entity.Collection;
import com.example.onaffair.service.CollectionService;
import com.example.onaffair.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/collection")
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @GetMapping("/collectionList")
    @ResponseBody
    public Result<List<Integer>> getCollections(@Param("userId") Integer userId){
        List<Collection> collections = collectionService.findCollectionsByCondition(new Collection(){{
            setUserId(userId);
        }});

        List<Integer> res = collections.stream().map(Collection::getMovieId).toList();

        return new Result<>(Result.success().getCode(), "请求成功", res);
    }
    @PostMapping("/changeCollection")
    @ResponseBody
    public Result<String> changeCollection(@RequestBody Collection collection){
        Collection co = collectionService.findCollectionById(collection.getUserId(), collection.getMovieId());
        if (co == null){
            collectionService.addCollection(collection);
            return new Result<>(Result.success().getCode(), "添加成功",null);
        }else{
            collectionService.deleteCollection(collection.getUserId(), collection.getMovieId());
            return new Result<>(Result.success().getCode(), "删除成功",null);
        }
    }

    @GetMapping("/getCollection")
    @ResponseBody
    public Result<List<CollectionResponseDto>> getCollection(@Param("movieIds") String movieIds){
        List<Integer> ids = Arrays.stream(movieIds.split(","))
                .map(Integer::valueOf)
                .toList();
        List<CollectionResponseDto> res = collectionService.getCollectionByMovieIdList(ids);

        return new Result<>(Result.success().getCode(), "请求成功", res);
    }
}
