package com.example.userservice.service;

import com.example.userservice.model.User;

import java.util.List;

public interface UserService {
    User login(String SID, String SPassword);

    List<User> getAllUsers();

    User getUserById(int SID);
}
