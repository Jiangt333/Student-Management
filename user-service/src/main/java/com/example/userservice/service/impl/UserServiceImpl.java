package com.example.userservice.service.impl;

import com.example.userservice.dao.UserDao;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String SID, String SPassword) {
        User myUser = userDao.findByUsernameAndPassword(SID, SPassword);
        if(myUser != null)
            return true;
        return  false;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> UserList = userDao.findAllUsers();
        return UserList;
    }

    @Override
    public User getUserById(String SID) {
        User User = userDao.findUserById(SID);
        return User;
    }

}
