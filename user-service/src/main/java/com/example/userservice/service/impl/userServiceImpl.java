package com.example.userservice.service.impl;

import com.example.userservice.dao.userDao;
import com.example.userservice.model.user;
import com.example.userservice.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class userServiceImpl implements userService {
    @Autowired
    private userDao UserDao;

    @Override
    public List<user> getAllUsers() {
        List<user> UserList = UserDao.findAllUsers();
        return UserList;
    }

    @Override
    public user getUserById(int id) {
        user User = UserDao.findUserById(id);
        return User;
    }

}
