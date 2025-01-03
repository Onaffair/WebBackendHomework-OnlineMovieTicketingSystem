package com.example.onaffair.service;

import com.example.onaffair.entity.User;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    boolean addUser(User user);

    boolean updateUser(User user);

    boolean deleteUser(Integer userId);

    List<User> findUsersByCondition(User user);
}
