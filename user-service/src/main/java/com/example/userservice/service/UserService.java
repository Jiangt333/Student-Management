package com.example.userservice.service;

import com.example.userservice.model.User;

import java.util.List;

public interface UserService {
    boolean login(String SID, String SPassword);

    List<User> getAllUsers();

    User getUserById(String SID);
}
