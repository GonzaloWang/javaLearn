package com.itheima.service;

import com.itheima.pojo.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    boolean addUser(User user);
    boolean deleteUser(Integer id);
    boolean updateUser(User user);
    User findUserByID(Integer id);
}
