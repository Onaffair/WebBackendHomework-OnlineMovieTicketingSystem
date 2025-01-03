package com.example.onaffair.service.impl;

import com.example.onaffair.entity.User;
import com.example.onaffair.mapper.UserMapper;
import com.example.onaffair.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class    UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> getAllUsers() {
        return userMapper.getAll();
    }

    @Override
    public boolean addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public boolean updateUser(User user) {
        return userMapper.update(user);
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return userMapper.delete(userId);
    }

    @Override
    public List<User> findUsersByCondition(User user) {
        return userMapper.selectByIf(user);
    }
}
