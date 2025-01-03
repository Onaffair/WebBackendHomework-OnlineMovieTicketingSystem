package com.example.onaffair.mapper;

import com.example.onaffair.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface UserMapper {
    List<User> getAll();

    boolean insert(User user);

    boolean update(User user);

    boolean delete(int userId);

    List<User> selectByIf(User user);
}
