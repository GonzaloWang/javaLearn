package com.itheima.service.impl;

import com.itheima.dao.UserMapper;
import com.itheima.pojo.User;
import com.itheima.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper mapper;

    @Override
    public List<User> findAll() {
        return mapper.findAll();
    }

    @Override
    public boolean addUser(User user) {
        return mapper.addUser(user) == 1;
    }

    @Override
    public boolean deleteUser(Integer id) {
        return mapper.deleteUser(id) == 1;
    }

    @Override
    public boolean updateUser(User user) {
        return mapper.updateUser(user) == 1;
    }

    @Override
    public User findUserByID(Integer id) {
        return mapper.findUserByID(id);
    }
}
